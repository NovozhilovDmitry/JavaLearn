package repository;

import json.customfield.fieldsdiscription.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OptionRepository {
    private static final Logger log = LoggerFactory.getLogger(OptionRepository.class);

    public void insertIntoTableOptions(Connection conn, Integer fieldId, List<Option> options) {
        String sql =
                """
                INSERT INTO options
                (componentid, id, name)
                VALUES
                (?,?,?)
                """;
        System.out.println("Попытка запись данные о ID:" + fieldId + " и его опциях: \n");
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Option option : options) {
                if (!option.isArchived()) {
                    ps.setInt(1, fieldId);
                    ps.setInt(2, option.getId());
                    ps.setString(3, option.getName());
                    ps.addBatch();
                    System.out.println(option.getId() + ": " + option.getName());
                }
            }
            ps.executeBatch();
        } catch (SQLException e) {
            log.error("Ошибка добавления данных: {}", e.getMessage());
        }
    }
}