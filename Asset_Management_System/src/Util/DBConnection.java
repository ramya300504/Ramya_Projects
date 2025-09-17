package Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBConnection {
    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        FileInputStream fis = new FileInputStream("C:\\Users\\ramya\\IdeaProjects\\JDBC\\src\\Util\\db.properties");
        Properties properties = new Properties();
        properties.load(fis);

        String driver = properties.getProperty("db.driver");
        String url = properties.getProperty("db.url");
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        return connection;
    }
}
