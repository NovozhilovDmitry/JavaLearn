package repository;

import json.customfield.fieldsdiscription.CustomField;
import json.customfield.fieldsdiscription.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CustomFieldRepository {
    private final OptionRepository optionRepository = new OptionRepository();
    private static final Logger log = LoggerFactory.getLogger(CustomFieldRepository.class);

    public void inserIntoTables(Connection conn, List<CustomField> fields) {
        String sql =
                """
                INSERT INTO components
                (id,
                 name)
                VALUES
                (?,?)
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            for (CustomField data: fields) {
                ps.setInt(1, data.getId());
                ps.setString(2, data.getName());
                ps.addBatch();
                List<Option> options = data.getOptions();
                if (options != null) {
                    optionRepository.insertIntoTableOptions(conn, data.getId(), options);
                }
            }
            ps.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            log.error("Ошибка добавления данных: {}", e.getMessage());
        }
    }


    }