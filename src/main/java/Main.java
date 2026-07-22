import api.JiraApi;
import api.TestCasesMultiLoader;
import bd.OracleConnection;
import bd.SelectStatements;
import json.customfield.CustomFieldParser;
import json.customfield.fieldsdiscription.CustomField;
import json.folders.FolderTreeExtractor;
import json.folders.FolderTreeParser;
import json.testcases.TestCasesParser;
import json.testcases.fieldsdiscription.*;
import json.testcasesinfo.TestCaseInfoParser;
import json.testcasesinfo.fieldsdiscription.TestCase;
import json.tkstatus.TestCaseStatusParser;
import json.tkstatus.fieldsdiscription.TestCaseStatus;
import orchestrator.Orchestrator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        Orchestrator orc = new Orchestrator();
        String connectline = orc.getBdConnectLine();
        String bdUser = orc.getBdUser();
        String bdPassword = orc.getBdPassword();
        int maxPoolSize = Integer.parseInt(orc.getMaxPoolSize());
        String userName = orc.getJiraUser();
        String password = orc.getJiraPassword();
        String statusUrl = orc.getJiraApiTestCaseStatusUrl();
        String customFieldsUrl = orc.getJiraApiCustomFieldsUrl();
        String folderTreeUrl = orc.getJiraApiFolderTreeUrl();
        String testCasesUrl = orc.getJiraApiTestCaseInfoUrl();
        String tasksUrl = orc.getJiraApiTestCaseUrl();
        List<Integer> getIdFromTkInfo;

        // Устанавливаем соединение к БД
        OracleConnection oraConnection = new OracleConnection(connectline, maxPoolSize, bdUser, bdPassword);

        // Создаем объекты
        JiraApi jira = new JiraApi("https://jira.cbr.ru", userName, password);
        CustomFieldRepository customFields = new CustomFieldRepository();
        TestCaseInfoRepository tkInfoRepository = new TestCaseInfoRepository();
        SelectStatements selects = new SelectStatements();
        TestCaseStatusRepository statusesRepository = new TestCaseStatusRepository();
        FolderTreeRepository foldersRepository = new FolderTreeRepository();
        CustomFieldParser parsingComponents = new CustomFieldParser();
        TestCaseInfoParser testCases = new TestCaseInfoParser();
        TestCaseStatusParser statusesParser = new TestCaseStatusParser();
        FolderTreeParser folderParser = new FolderTreeParser();
        TestCasesParser testCaseParser = new TestCasesParser();
        TestCaseServiceRepository testCaseService = new TestCaseServiceRepository();

        // Получаем данные о последней синхронизации
        Timestamp getSyncTkInfo = selects.getSyncInfo(oraConnection);

        // Получаем данные из Jira Api справочников
        String statusesData = jira.getDataFromUrl(statusUrl);
        String foldersData = jira.getDataFromUrl(folderTreeUrl);
        String componentsData = jira.getDataFromUrl(customFieldsUrl);
        String testCaseInfoData = jira.getDataFromUrl(testCasesUrl);

        // Парсим справочник статусов
        List<TestCaseStatus> statusesFields = statusesParser.parse(statusesData);
        statusesRepository.insertIntoTableStatuses(oraConnection, statusesFields);

        // Парсим справочник каталогов
        List<FolderTreeExtractor.Result> foldersFields = folderParser.parse(foldersData);
        foldersRepository.insertIntoTableTkFolders(oraConnection, foldersFields);

        // Парсим справочник компонентов и вставляем в таблицу
        List<CustomField> componentsFields = parsingComponents.parse(componentsData);
        customFields.inserIntoTableComponents(oraConnection, componentsFields);

        // Получаем словари справочников компонентов
        HashMap<Integer, String> selectOptionComponents = customFields.getOptionComponentsDict();
        HashMap<Integer, String> selectMainComponents = customFields.getMainComponentsDict();

        // Парсим общую информацию о ТК и вставляем в таблицу
        List<TestCase> parseTestCaseInfo = testCases.parseResults(testCaseInfoData);
        tkInfoRepository.insertIntoTkInfo(oraConnection, parseTestCaseInfo, selectOptionComponents, selectMainComponents);

        if (getSyncTkInfo != null) {
            getIdFromTkInfo = tkInfoRepository.getIdFromTkInfo(oraConnection);
            log.info("Данные будут обновлены по {} тест-кейсам", getIdFromTkInfo.size());
        } else {
            getIdFromTkInfo = tkInfoRepository.getIdFromTkInfoWithoutCondition(oraConnection);
            log.info("Первый запуск программы");
        }

        if (!getIdFromTkInfo.isEmpty()) {
            System.out.println("Отправляем запросы на обновление шагов ТК");
            TestCasesMultiLoader loader = new TestCasesMultiLoader(jira, oraConnection, testCaseParser,
                    testCaseService, tasksUrl, maxPoolSize);
            loader.loadTestCases(getIdFromTkInfo);
        }

        oraConnection.close();

    }
}