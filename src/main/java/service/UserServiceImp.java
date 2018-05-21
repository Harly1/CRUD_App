package service;

import dao.UserDAO;
import dao.UserDaoHibernteImp;
import dao.UserDaoJDBCImp;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.internal.SessionImpl;
import util.DbHelper;
import util.HibernateSessionFactoryUtil;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImp implements UserService   {

   private Connection connection = getHibernetConnection();
   private UserDAO userDAO = new UserDaoHibernteImp();

//    private Connection connection = DbHelper.getJdbcConnection();
//    private UserDAO userDAO = new UserDaoJDBCImp(connection);

    @Override
    public Connection getJdbcConnection() {
        Connection jdbcConn = null;
        jdbcConn = DbHelper.getJdbcConnection();
        return jdbcConn;
    }

    @Override
    public Connection getHibernetConnection() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        SessionImpl sessionImpl = (SessionImpl) session;
        Connection hibernetConn = sessionImpl.connection();
        session.close();
        return hibernetConn;
    }

    @Override
    public void printConnectInfo() throws SQLException {
        DatabaseMetaData dmd = connection.getMetaData();
        String url = dmd.getURL();
        String user = dmd.getUserName();
        String dbName = dmd.getDatabaseProductName();
        System.out.println("url= "+url+ '\n'+" user= "+user+ '\n'+ " database= "+dbName);
    }

    @Override
    public void deleteUser(User user) throws SQLException { userDAO.deleteUser(user); }

    @Override
    public void editUser(int id) throws SQLException { userDAO.getUser(id); }

    @Override
    public void insertUser(User user) throws SQLException { userDAO.insertUser(user); }

    @Override
    public void updateUser(User user) throws SQLException { userDAO.updateUser(user); }

    @Override
    public List<User> listUser() throws SQLException { return userDAO.listAllUsers(); }

    @Override
    public User getUser(int id) throws SQLException { return userDAO.getUser(id); }

}

