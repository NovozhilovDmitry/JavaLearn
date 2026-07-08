package repository;

import json.customfield.fieldsdiscription.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OptionRepository {
    private static final Logger log = LoggerFactory.getLogger(OptionRepository.class);

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