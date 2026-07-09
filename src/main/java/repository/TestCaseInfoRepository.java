package repository;

import json.testcasesinfo.fieldsdiscription.TestCase;

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

//        """
//                MERGE into tkfolders c
//                USING ( select ? id, ? name from dual) t
//                ON (c.id=t.id)
//                WHEN MATCHED THEN
//                    UPDATE SET c.name=t.name
//                WHEN NOT MATCHED THEN
//                    INSERT (id, name) values (t.id, t.name)
//                """

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, info.getId());
        ps.setString(2, info.getKey());
        ps.setString(3, info.getName());
        ps.setString(4, info.getUpdatedBy());
        ps.setDate(5, (Date) info.getUpdatedOn());
        ps.setInt(6, info.getFolderId());
        ps.executeUpdate();

        //todo: AddBanch
        // for(CustomField field : fields) {
        //            switch (field.getName()) {
        //                case "server":
        //                    serverRepository.insertIntoTable("");
        //                case "fucntionality":
        //                    System.out.println("insert into functionalities");
        //                case "approvedby":
        //                    System.out.println("insert into approvedby");
        //                case "arms":
        //                    System.out.println("insert into arms");
        //                case "components":
        //                    System.out.println("insert into components");
        //                case "options":
        //                    System.out.println("insert into options");
        //                case "testingtypes":
        //                    System.out.println("insert into testingtypes");
        //                case "tkreleases":
        //                    System.out.println("insert into tkreleases");
        //                case "userroles":
        //                    System.out.println("insert into userroles");
    }
}
