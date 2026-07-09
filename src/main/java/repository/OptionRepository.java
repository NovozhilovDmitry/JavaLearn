package repository;

import json.customfield.fieldsdiscription.Option;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OptionRepository {
    public void insertIntoTableOptions(PreparedStatement ps, List<Option> options) throws SQLException {
        for (Option option : options) {
            if (!option.isArchived()) {
                ps.setInt(2, option.getId());
                ps.setString(3, option.getName());
                ps.addBatch();
            }
        }
        ps.executeBatch();
    }
}