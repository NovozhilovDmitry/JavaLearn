package repository;

import json.getterFields.CustomField;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CustomFieldRepository {

    public void save(Connection connection, CustomField field) throws Exception {
        String sql =
                """
                INSERT INTO custom_field
                (id,
                 name,
                 field_type,
                 archived,
                 required,
                 project_id)
                VALUES
                (?,?,?,?,?,?)
                """;
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, field.getId());
        ps.setString(2, field.getName());
        ps.setString(3, field.getType());
        ps.setInt(4, field.isArchived() ? 1 : 0);
        ps.setInt(5, field.isRequired() ? 1 : 0);
        ps.setInt(6, field.getProjectId());
        ps.executeUpdate();
    }

}