package service;

import dao.UserDAO;
import dao.UserDaoHibernteImp;
import dao.UserDaoJDBCImp;
import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import util.DbHelper;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImp implements UserService {
   private Connection connection = getConnection();
//   private UserDAO userDAO = new UserDaoJDBCImp(connection);
   private UserDAO userDAO = new UserDaoHibernteImp(connection);

   private static final String hibernate_show_sql = "true";
   private static final String hibernate_hbm2ddl_auto = "create";


    @Override
    public void printConnectInfo() throws SQLException {
        DatabaseMetaData dmd = connection.getMetaData();
        String url = dmd.getURL();
        String user = dmd.getUserName();
        String dbName = dmd.getDatabaseProductName();
        System.out.println("url= "+url+ '\n'+" user= "+user+ '\n'+ " database= "+dbName);
    }

    @Override
    public void deleteUser(User user) throws SQLException {
        userDAO.deleteUser(user);
    }

    @Override
    public void editUser(int id) throws SQLException {
        userDAO.getUser(id);
    }

    @Override
    public void insertUser(User user) throws SQLException {
        userDAO.insertUser(user);
    }

    @Override
    public void updateUser(User user) throws SQLException {
        userDAO.updateUser(user);
    }

    @Override
    public List<User> listUser() throws SQLException {
       return userDAO.listAllUsers();
    }

    @Override
    public User getUser(int id) throws SQLException {
        return userDAO.getUser(id);
    }

    public Connection getConnection() {
        return new DbHelper().getJdbcConnection();
    }




}

