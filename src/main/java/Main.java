import api.JiraApi;
import bd.OracleConnection;
import bd.SqliteConnect;
import com.fasterxml.jackson.databind.JsonNode;
import json.customfield.CustomFieldParser;
import json.customfield.fieldsdiscription.CustomField;
import json.folders.FolderTreeExtractor;
import json.folders.FolderTreeParser;
import json.testcasesinfo.TestCaseParser;
import json.testcasesinfo.fieldsdiscription.TestCase;
import json.tkstatus.TestCaseStatusParser;
import json.tkstatus.fieldsdiscription.TestCaseStatus;
import orchestrator.Orchestrator;
import repository.*;

import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        String dataFilePath = "src/main/resources/data/";
        Orchestrator orc = new Orchestrator();
        String connectline = orc.getBdConnectLine();
        String bdUser = orc.getBdUser();
        String bdPassword = orc.getBdPassword();
        JiraApi jira = new JiraApi("https://jira.cbr.ru");
        String userName = orc.getJiraUser();
        String password = orc.getJiraPassword();
//        String statusUrl = orc.getJiraApiTestCaseStatusUrl(); // Готово
//        String customFieldsUrl = orc.getJiraApiCustomFieldsUrl(); // Готово
//        String folderTreeUrl = orc.getJiraApiFolderTreeUrl(); // Готово
        String testCasesUrl = orc.getJiraApiTestCasesUrl();
        String maxWorkers = orc.getMaxWorkers();

        OracleConnection ora = new OracleConnection();
        ora.connect(connectline, bdUser, bdPassword);


        SqliteConnect connection = new SqliteConnect();
//        connection.connect("mydb.db");
//        connection.executeUpsert("""
//                create table if not exists tk_info (
//                id integer,
//                key text,
//                name text,
//                statusid integer,
//                folderid integer,
//                owner text,
//                updatedby text,
//                updatedon date);
//
//                create table if not exists components (id integer, name text);
//
//                create table if not exists tkcommonfields (
//                tk_id integer,
//                approvedby name,
//                releases text,
//                testingtypes text,
//                servers text,
//                userroles text,
//                functionalities text,
//                apm text);
//                """);
        // Создали объекты
//        CustomFieldRepository service = new CustomFieldRepository(connection);
//        TestCaseParser customParser = new TestCaseParser();
        // Парсим справочник компонентов и вставляем в таблицу
//        CustomFieldParser parsingComponent = new CustomFieldParser();
//        List<CustomField> componentsFields = parsingComponent.parse(dataFilePath + "customfields.json");
//        service.inserIntoTableComponents(componentsFields);
        // Получаем словарь справочника компонентов
//        HashMap<Integer, String> selectComponents = service.getComponentsDict();
        // Парсим общую информацию о ТК и вставляем в таблицу
//        List<TestCase> parseTestCaseInfo = customParser.parseResults(dataFilePath + "tk_info.json");
//        TestCaseInfoRepository tkInfoRepository = new TestCaseInfoRepository(connection);
//        tkInfoRepository.insertIntoTkInfo(parseTestCaseInfo, selectComponents);
//        tkInfoRepository.insertIntoTkInfo(parseTestCaseInfo);
//        connection.disconnect();

    }
}
