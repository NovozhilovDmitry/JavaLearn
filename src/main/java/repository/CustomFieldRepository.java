package repository;

import bd.OracleConnect;
import bd.SqliteConnect;
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
    private final HashMap<String, Integer> idComponents = new HashMap<>();

    public CustomFieldRepository(OracleConnect connection) {
        this.conn = connection.getConnection();
        fillDict(idComponents);
    }

    public void inserIntoTableComponents(List<CustomField> fields) {
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
                updateDict(idComponents, data.getName(), data.getId());
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
            conn.commit();
            log.info("Внесены данные в таблицу COMPONENTS");
        } catch (SQLException e) {
            log.error("Ошибка добавления данных: {}", e.getMessage(), e);
        }
    }

    private void fillDict(HashMap<String, Integer> dict) {
        dict.put("Сервер", null);
        dict.put("Вид тестирования", null);
        dict.put("Утвердивший ТК техлид", null);
        dict.put("Релиз, для которого ТК актуализирован", null);
        dict.put("Роль пользователя", null);
        dict.put("Функциональность", null);
        dict.put("АРМ (начальный статус)", null);
    }

    private void updateDict(HashMap<String, Integer> dict, String name, Integer value) {
        if (dict.containsKey(name)) {
            dict.replace(name, value);
        }
    }

    public HashMap<Integer, String> getOptionComponentsDict() {
        return this.components;
    }

    public HashMap<String, Integer> getMainComponentsDict() {
        return this.idComponents;
    }

    }