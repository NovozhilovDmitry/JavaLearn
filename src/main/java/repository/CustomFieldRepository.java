package repository;

import bd.OracleConnect;
import json.customfield.fieldsdiscription.CustomField;
import json.customfield.fieldsdiscription.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class CustomFieldRepository {
    private final Connection conn;
    private static final Logger log = LoggerFactory.getLogger(CustomFieldRepository.class);
    private final HashMap<Integer, String> components = new HashMap<>();
    private final HashMap<Integer, String> idComponents = new HashMap<>();

    public CustomFieldRepository(OracleConnect connection) {
        this.conn = connection.getConnection();
    }

    public void inserIntoTableComponents(List<CustomField> fields) throws SQLException {
        String sql =
                """
                MERGE into components c
                USING ( select ? id, ? name from dual) t
                ON (c.id=t.id)
                WHEN MATCHED THEN
                    UPDATE SET c.name=t.name
                WHEN NOT MATCHED THEN
                    INSERT (id, name) values (t.id, t.name)
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            for (CustomField data: fields) {
                ps.setInt(1, data.getId());
                ps.setString(2, data.getName());
                fillDict(idComponents, data.getName(), data.getId());
                components.put(data.getId(), data.getName());
                ps.addBatch();
                List<Option> options = data.getOptions();
                if (options != null) {
                    for (Option option : options) {
                        if (!option.isArchived()) {
                            ps.setInt(1, option.getId());
                            ps.setString(2, option.getName());
                            components.put(option.getId(), option.getName());
                            ps.addBatch();
                        }
                    }
                }
            }
            ps.executeBatch();
            log.info("Внесены данные в таблицу COMPONENTS");
            new SyncInfoRepository(conn).insertIntoTableSyncInfo("COMPONENTS");
            conn.commit();
        } catch (SQLException e) {
            log.error("Ошибка добавления данных: {}", e.getMessage(), e);
            conn.rollback();
        }
    }

    private void fillDict(HashMap<Integer, String> dict, String name, Integer value) {
        switch (name) {
            case "Сервер",
                 "Вид тестирования",
                 "Утвердивший ТК техлид",
                 "Релиз, для которого ТК актуализирован",
                 "Роль пользователя",
                 "Функциональность",
                 "АРМ (начальный статус)":
                dict.put(value, name);
        }
    }

    public HashMap<Integer, String> getOptionComponentsDict() {
        return this.components;
    }

    public HashMap<Integer, String> getMainComponentsDict() {
        return this.idComponents;
    }

    }