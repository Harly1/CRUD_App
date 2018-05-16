package util;

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

       try( InputStream reader = new FileInputStream("C:\\Users\\KrVl8001\\IdeaProjects\\CRUD_App\\configSQL.properties")){
           properties.load(reader);

           this.jdbcURL = properties.getProperty("dburl");
           this.jdbcUsername =  properties.getProperty("dbuser");
           this.jdbcPassword =  properties.getProperty("dbpassword");

           DriverManager.registerDriver((Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance());

           this.jdbcConnection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword) ;

       } catch (Exception e){
            e.printStackTrace();
       }
    }

    public Connection getJdbcConnection() {
        return jdbcConnection;
    }
}