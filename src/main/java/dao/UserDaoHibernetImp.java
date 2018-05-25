package dao;

import model.User;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionImpl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernetImp implements UserDAO {

    private  SessionFactory sessionFactory;

    public UserDaoHibernetImp(Configuration configuration){

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());

        sessionFactory = configuration.buildSessionFactory(builder.build());
    }

    public User getUser(int id) {
        return (User) sessionFactory.openSession().get(User.class, id);
    }

    public  Connection getHibernetConnection() {
        Session session = sessionFactory.openSession();
        SessionImpl sessionImpl = (SessionImpl) session;
        Connection hibernetConn = sessionImpl.connection();
        session.close();
        return hibernetConn;
    }

    @Override
    public User getUserByName(String name) throws SQLException {
        Query query =  sessionFactory.openSession().createQuery("FROM User WHERE user_name = :paramName");
        query.setParameter("paramName", name);
        User user = (User) query.uniqueResult();
        return user;
    }

    @Override
    public void printConnectInfo() throws SQLException {
        DatabaseMetaData dmd = getHibernetConnection().getMetaData();
        String url = dmd.getURL();
        String user = dmd.getUserName();
        String dbName = dmd.getDatabaseProductName();
        System.out.println("url= "+url+ '\n'+" user= "+user+ '\n'+ " database= "+dbName);
    }

    public boolean insertUser(User user) {
        try {
            Session session = sessionFactory.openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(user);
            tx1.commit();
            session.close();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUser(User user) {
        try {
            Session session = sessionFactory.openSession();
            Transaction tx1 = session.beginTransaction();
            session.update(user);
            tx1.commit();
            session.close();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(User user) {
        try {
            Session session = sessionFactory.openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(user);
            tx1.commit();
            session.close();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> listAllUsers() {
        List<User> users = (List<User>) sessionFactory.openSession().createQuery("FROM User").list();
        return users;
    }
}
