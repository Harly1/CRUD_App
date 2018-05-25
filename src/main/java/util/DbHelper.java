package util;

import model.User;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Properties;

public class DbHelper {

    private static Connection jdbcConnection;

    public static Connection getJdbcConnection()  {
        String jdbcURL;
        String jdbcUsername;
        String jdbcPassword;
        Properties properties = new Properties();

        try (InputStream reader = new FileInputStream("C:\\Users\\KrVl8001\\IdeaProjects\\CRUD_App\\src\\main\\resources\\configJDBC.properties")) {
            properties.load(reader);
            jdbcURL = properties.getProperty("dburl");
            jdbcUsername = properties.getProperty("dbuser");
            jdbcPassword = properties.getProperty("dbpassword");

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            return jdbcConnection;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Configuration getConfiguration(){
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(User.class);
        return configuration;
    }

}
