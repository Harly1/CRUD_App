package factory;

import dao.UserDAO;
import dao.UserDaoHibernetImp;
import dao.UserDaoJDBCImp;
import util.DbHelper;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactory {
    private static UserDAO userDao;

    private UserDaoFactory(){}

    public static UserDAO getDao(){
        if(userDao == null){
            try(InputStream read = new FileInputStream("C:\\Users\\KrVl8001\\IdeaProjects\\CRUD_App\\src\\main\\resources\\db.properties")){
                Properties properties = new Properties();
                properties.load(read);
                String realization = properties.getProperty("userDao");
                System.out.println("DAO_realization = " + realization);
                return realization.equals("hibernet") ? new UserDaoHibernetImp(DbHelper.getConfiguration()) : new UserDaoJDBCImp(DbHelper.getJdbcConnection());
            } catch (Exception e){
                return null;
            }
        }
        return userDao;
    }
}
