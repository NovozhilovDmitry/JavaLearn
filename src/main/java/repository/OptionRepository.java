package repository;

import json.getterFields.Option;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class OptionRepository {

    public void save(Connection connection, Integer fieldId, Option option) throws Exception {
        String sql =
                """
                INSERT INTO custom_field_option
                (id,
                 custom_field_id,
                 name,
                 option_index,
                 archived)
                VALUES
                (?,?,?,?,?)
                """;
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, option.getId());
        ps.setInt(2, fieldId);
        ps.setString(3, option.getName());
        ps.setInt(4, option.getIndex());
        ps.setInt(5, option.isArchived() ? 1 : 0);
        ps.executeUpdate();
    }

}