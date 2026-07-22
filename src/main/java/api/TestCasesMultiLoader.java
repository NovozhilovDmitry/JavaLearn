package api;

import bd.OracleConnection;
import json.testcases.TestCasesParser;
import json.testcases.fieldsdiscription.TestCaseMainInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.TestCaseServiceRepository;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.*;

public class TestCasesMultiLoader {
    private static final Logger log = LoggerFactory.getLogger(TestCasesMultiLoader.class);
    private final String testCaseUrl;
    private final JiraApi jiraApi;
    private final OracleConnection oracleConnect;
    private final TestCasesParser parser;
    private final TestCaseServiceRepository repository;
    private final CompletionService<Integer> completionService;
    private final ExecutorService executor;

    public TestCasesMultiLoader(JiraApi jiraApi,
                                OracleConnection oracleConnect,
                                TestCasesParser testCaseParser,
                                TestCaseServiceRepository testCaseService,
                                String url,
                                int threadCount) {
        this.executor = Executors.newFixedThreadPool(threadCount);
        this.jiraApi = jiraApi;
        this.oracleConnect = oracleConnect;
        this.parser = testCaseParser;
        this.repository = testCaseService;
        this.completionService = new ExecutorCompletionService<>(executor);
        this.testCaseUrl = url;
    }

    public void loadTestCases(List<Integer> ids) throws InterruptedException {
        try {
            for (Integer id : ids) {
                completionService.submit(() -> loadSingleTestCase(id));
            }

            for (int i = 0; i < ids.size(); i++) {
                Future<Integer> future = completionService.take();
                try {
                    Integer finishedId = future.get();
                    log.debug("{} успешно обработан", finishedId);
                } catch (ExecutionException e) {
                    log.error("Ошибка обработки тест-кейса: ", e.getCause());
                }
            }
            log.info("Информация по всем тест-кейсам внесена в базу данных");
        } finally {
            executor.shutdown();

            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                executor.shutdownNow();
            }
        }
    }

    private Integer loadSingleTestCase(Integer id) throws IOException, InterruptedException, SQLException {
        long start = System.nanoTime();
        String replStr = testCaseUrl.replace("{task}", id.toString());
        String dataTestCase = jiraApi.getDataFromUrl(replStr);
        TestCaseMainInformation testCaseValues = parser.parseResults(dataTestCase);
        try (Connection conn = oracleConnect.getConnection()) {
            repository.insertSteps(conn, testCaseValues);
        }
        log.debug("{} обработан за {} мс", id, Duration.ofNanos(System.nanoTime()-start).toMillis());
        return id;
    }
}