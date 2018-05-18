package util;

import model.User;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Properties;

public class DbHelper {
    private  String jdbcURL;
    private  String jdbcUsername;
    private  String jdbcPassword;
    private  Connection jdbcConnection;

    public DbHelper(){
        Properties properties = new Properties();

       try( InputStream reader = new FileInputStream("C:\\Users\\KrVl8001\\IdeaProjects\\CRUD_App\\configJDBC.properties")){
           properties.load(reader);

           this.jdbcURL = properties.getProperty("dburl");
           this.jdbcUsername =  properties.getProperty("dbuser");
           this.jdbcPassword =  properties.getProperty("dbpassword");
//           DriverManager.registerDriver((Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance());
           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           this.jdbcConnection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword) ;

       } catch (Exception e){
            e.printStackTrace();
       }
    }

    public Connection getJdbcConnection() {
        return jdbcConnection;
    }

    public Configuration getHibernetConfiguration() {
        Properties properties = new Properties();
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        try( InputStream reader = new FileInputStream("C:\\Users\\KrVl8001\\IdeaProjects\\CRUD_App\\configHibernet.properties")){
            properties.load(reader);

        } catch (Exception e){

        }

        configuration.setProperty("hibernate.hbm2ddl.auto", properties.getProperty("hibernate.hbm2ddl.auto"));
        configuration.setProperty("hibernate.show_sql",properties.getProperty("hibernate.show_sql"));
        configuration.setProperty("hibernate.dialect",properties.getProperty("hibernate.dialect"));
        configuration.setProperty("hibernate.connection.driver_class",properties.getProperty("hibernate.connection.driver_class"));
        configuration.setProperty("hibernate.connection.url",properties.getProperty("hibernate.connection.url"));
        configuration.setProperty("hibernate.connection.username",properties.getProperty("hibernate.connection.username"));
        configuration.setProperty("hibernate.connection.password",properties.getProperty("hibernate.connection.password"));


        return configuration;
    }

}
