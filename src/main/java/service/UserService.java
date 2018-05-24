package service;

import dao.UserDAO;
import model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserService {
     void printConnectInfo() throws SQLException;
   /*  Connection getJdbcConnection();
     Connection getHibernetConnection();*/

     void deleteUser(User user)    throws SQLException;
     void editUser  (int id)       throws SQLException;
     void insertUser(User user)    throws SQLException;
     void updateUser(User user)    throws SQLException;

     List<User> listUser ()        throws SQLException;
     User getUser(int id)          throws SQLException;
     User getUserByName(String name) throws SQLException;
}
