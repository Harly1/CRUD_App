package service;

import dao.UserDAO;
import factory.UserDaoFactory;
import model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImp implements UserService   {
    UserDAO userDAO;

    public UserServiceImp(){
         this.userDAO = UserDaoFactory.getDao();
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

    @Override
    public User getUserByName(String name) throws SQLException {
        return userDAO.getUserByName(name);
    }

}

