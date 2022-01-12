package by.lamaka.servlets.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDB {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        String url = "jdbc:postgresql://localhost:5432/tms";
        String user = "postgres";
        String password = "postgres";
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url,user,password);
    }
}
