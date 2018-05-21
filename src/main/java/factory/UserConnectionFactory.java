package factory;

import dao.UserDAO;
import dao.UserDaoHibernetImp;
import dao.UserDaoJDBCImp;
import util.DbHelper;
import util.HibernateSessionFactoryUtil;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import static util.DbHelper.getJdbcConnection;
import static util.HibernateSessionFactoryUtil.getHibernetConnection;

public class UserConnectionFactory {
    private static Connection connection;

    private UserConnectionFactory(){}

    public static Connection getConnection(){
        if(connection == null){
            try(InputStream read = new FileInputStream("C:\\Users\\KrVl8001\\IdeaProjects\\CRUD_App\\src\\main\\resources\\db.properties")){
                Properties properties = new Properties();
                properties.load(read);
                String realization = properties.getProperty("userDao");
                return realization.equals("hibernet") ? getHibernetConnection(): getJdbcConnection();
            } catch (Exception e){
                return null;
            }
        }
        return connection;
    }
}
