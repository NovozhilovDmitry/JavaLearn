import json.getterFields.TestCaseStatus;
import json.parser.TestCaseStatusParser;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        String dataFilePath = "src/main/resources/data/";
//        Orchestrator orc = new Orchestrator();
//        JiraApi jira = new JiraApi();
//        String USERNAME = orc.getJiraUser();
//        String PASSWORD = orc.getJiraPassword();
//        String authEndpoint = orc.getJiraApiAuthorizationUrl();
//        String cfieldsEndpoint = orc.getJiraApiCustomFieldsUrl();
//
//        try {
//            System.out.println("Шаг 1: Авторизация и открытие сессии в Jira...");
//            boolean loginSuccess = jira.createJiraSession(USERNAME, PASSWORD, authEndpoint);
//
//            if (loginSuccess) {
//                System.out.println("Успешно! Сессия создана. Пароль больше не потребуется.\n");
//                System.out.println("Получение данных о каталогах (используется кука сессии)...");
//                String issueData = jira.fetchJiraDataNA(cfieldsEndpoint);
//                System.out.println("Данные: \n" + issueData);
//
//            } else {
//                System.err.println("Не удалось авторизоваться. Проверьте логин и пароль.");
//            }
//
//        } catch (Exception e) {
//            System.err.println("Произошла ошибка сетевого взаимодействия: " + e.getMessage());
//        }
//        CustomFieldParser parser = new CustomFieldParser();
//        List<JsonCustomField> fields = parser.parse("testcase.json");
//        Connection connection = OracleConnection.getConnection();
//        CustomFieldImportService service = new CustomFieldImportService();
//        service.importFields(connection, fields);
//        connection.close(); // в самом конце будет



    }
}
