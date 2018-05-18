package dao;

import model.User;
import org.hibernate.*;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import util.DbHelper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernteImp implements UserDAO {

    private Connection jdbcConnection;

    public UserDaoHibernteImp(Connection jdbcConnection) {

        this.jdbcConnection = jdbcConnection;

    }

    private SessionFactory sessionFactory = new DbHelper().getHibernetConfiguration().buildSessionFactory();

    @Override
    public boolean insertUser(User user) {
       try {
           Session session = sessionFactory.openSession();
           Transaction transaction = session.beginTransaction();
           session.save(user);
           transaction.commit();
           session.close();
           return true;
       } catch (Exception e){
           return false;
       }
    }

    @Override
    public List<User> listAllUsers() {
        List<User> listUser = new ArrayList<User>();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from User");
        listUser = session.createCriteria(User.class).list();
        listUser = query.list();
        return listUser;
    }

    @Override
    public boolean updateUser(User user) {
       try {
           Session session = sessionFactory.openSession();
           Transaction transaction = session.beginTransaction();
           User newUser = (User) session.load(User.class, user.getId());
           newUser.setName(user.getName());
           newUser.setPassword(user.getPassword());
           session.update(newUser);
           transaction.commit();
           session.close();
           return true;
       } catch (Exception e){
           return false;
       }
    }

    @Override
    public User getUser(int id) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.createCriteria(User.class,"users").add(Restrictions.eq("users.id", id));

        return user ;
    }

    @Override
    public boolean deleteUser(User user) {
        int id = user.getId();
       try {
           Session session = sessionFactory.openSession();
           Transaction transaction = session.beginTransaction();

           User newUser = (User) session.get(User.class, id);

           session.delete(newUser);
           transaction.commit();
           session.close();
           return true;
       } catch (Exception e){
           return false;
    }

 }

}
