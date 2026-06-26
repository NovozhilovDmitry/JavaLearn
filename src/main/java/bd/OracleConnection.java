package bd;

import orchestrator.Orchestrator;
import java.sql.Connection;
import java.sql.DriverManager;

public class OracleConnection {
    static Orchestrator orc = new Orchestrator();
    private static final String connectionLine = orc.getBdConnectLine();
    private static final String URL = "jdbc:oracle:thin:@" + connectionLine;
    private static final String bdUser = orc.getBdUser();
    private static final String bdPassword = orc.getBdPassword();

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, bdUser, bdPassword);
    }
}
