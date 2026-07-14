package repository;

import json.testcasesinfo.fieldsdiscription.CustomFieldValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestCaseCommonFieldsRepository {
    private static final Logger log = LoggerFactory.getLogger(TestCaseCommonFieldsRepository.class);

    public void insertIntoTkCommonFields(Connection conn,
                                         Integer tkId,
                                         List<CustomFieldValue> customField,
                                         HashMap<Integer, String> optionComponents,
                                         HashMap<Integer, String> mainComponents) {
        String sqlTkCommonFields =
                """
                MERGE into tkcommonfields c
                USING ( select ? tk_id,
                               ? approvedby,
                               ? releases,
                               ? testingtypes,
                               ? servers,
                               ? userroles,
                               ? functionalities,
                               ? apm from dual) t
                ON (c.tk_id=t.tk_id)
                WHEN MATCHED THEN UPDATE SET
                    c.approvedby=t.approvedby,
                    c.releases=t.releases,
                    c.testingtypes=t.testingtypes,
                    c.servers=t.servers,
                    c.userroles=t.userroles,
                    c.functionalities=t.functionalities,
                    c.apm=t.apm
                WHEN NOT MATCHED THEN INSERT
                    (tk_id, approvedby, releases, testingtypes, servers, userroles, functionalities, apm)
                    VALUES (t.tk_id, t.approvedby, t.releases, t.testingtypes, t.servers, t.userroles, t.functionalities, t.apm)
                """;

        try (PreparedStatement ps = conn.prepareStatement(sqlTkCommonFields)) {

            ps.setInt(1, tkId);
            ps.setNull(2, Types.VARCHAR);
            ps.setNull(3, Types.VARCHAR);
            ps.setNull(4, Types.VARCHAR);
            ps.setNull(5, Types.VARCHAR);
            ps.setNull(6, Types.VARCHAR);
            ps.setNull(7, Types.VARCHAR);
            ps.setNull(8, Types.VARCHAR);

            for (CustomFieldValue value: customField) {
                Integer intData = value.getCustomFieldId();
                List<String> tempValue = new ArrayList<>();
                String strData = mainComponents.get(intData);

                if (strData != null) {
                    String strValue = value.getStringValue();
                    Integer intValue = value.getIntValue();

                    if (strValue != null && !strValue.isEmpty()) {
                        String[] ids = strValue.substring(1, strValue.length() - 1)
                                .split("-");
                        for (String b: ids) {
                            String option = optionComponents.get(Integer.parseInt(b));
                            if (option != null) {
                                tempValue.add(option);
                            }
                        }
                        String data = String.join("; ", tempValue);
                        switch (strData) {
                            case "Вид тестирования":
                                ps.setString(4, data);
                                break;
                            case "Сервер":
                                ps.setString(5, data);
                                break;
                            case "Роль пользователя":
                                ps.setString(6, data);
                                break;
                            case "Функциональность":
                                ps.setString(7, data);
                                break;
                            case "АРМ (начальный статус)":
                                ps.setString(8, data);
                                break;
                        }
                    } else if (intValue != null && intValue > 0) {
                        switch (strData) {
                            case "Утвердивший ТК техлид":
                                ps.setString(2, optionComponents.get(intValue));
                                break;
                            case "Релиз, для которого ТК актуализирован":
                                ps.setString(3, optionComponents.get(intValue));
                                break;
                        }
                    }
                }
            }
            ps.addBatch();
            ps.executeBatch();
        } catch (SQLException e) {
            log.error("Ошибка добавления данных: {}", e.getMessage(), e);
        }
    }
}
