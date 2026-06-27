package repository;

import json.components.fieldsdiscription.TestCase;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Connection;

public class TestCaseInfoRepository {

    public void save(Connection connection, TestCase info) throws Exception {
        String sql =
                """
                INSERT INTO tk_info
                (
                )
                VALUES
                (?,?,?,?,?,?)
                """;
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, info.getId());
        ps.setString(2, info.getKey());
        ps.setString(3, info.getName());
        ps.setString(4, info.getUpdatedBy());
        ps.setDate(5, (Date) info.getUpdatedOn());
        ps.setInt(6, info.getFolderId());
        ps.executeUpdate();
    }
}
