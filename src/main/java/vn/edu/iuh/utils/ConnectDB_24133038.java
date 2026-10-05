package vn.edu.iuh.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB_24133038 {
    private static final String URL = "jdbc:sqlserver://127.0.0.1:1433;databaseName=TestQT_60;encrypt=false;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASS = "12345";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
