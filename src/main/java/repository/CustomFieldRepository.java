package repository;

import bd.SqliteConnect;
import json.customfield.fieldsdiscription.CustomField;
import json.customfield.fieldsdiscription.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CustomFieldRepository {
    private final Connection conn;
    private static final Logger log = LoggerFactory.getLogger(CustomFieldRepository.class);

    public CustomFieldRepository(SqliteConnect connection) {
        this.conn = connection.getConnection();
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
                ps.addBatch();
                List<Option> options = data.getOptions();
                if (options != null) {
                    for (Option option : options) {
                        if (!option.isArchived()) {
                            ps.setInt(1, option.getId());
                            ps.setString(2, option.getName());
                            ps.addBatch();
                        }
                    }
                }
            }
            ps.executeBatch();
            conn.commit();
            log.info("Внесены данные в таблицу COMPONENTS");
        } catch (SQLException e) {
            log.error("Ошибка добавления данных: {}", e.getMessage());
        }
    }


    }