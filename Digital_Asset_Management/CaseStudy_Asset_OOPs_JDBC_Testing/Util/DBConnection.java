package Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static Connection connection;

    //The constructor is created as private inorder to prevent instantiation
    private DBConnection() {
    }

    //Static method which returns connection
    //To get connecction properties load from properties file
    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        FileInputStream fis = new FileInputStream("C:\\Users\\ramya\\IdeaProjects\\Digital_Asset_Management\\CaseStudy_Asset_OOPs_JDBC_Testing\\Util\\db.properties");
        Properties properties = new Properties();
        properties.load(fis);
        String driver = properties.getProperty("db.driver");
        String url = properties.getProperty("db.url");
        String username = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");
        Class.forName(driver);
        connection = DriverManager.getConnection(url, username, password);

        return connection;

    }

}
