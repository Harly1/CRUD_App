package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    boolean insertUser(User user) throws SQLException;
    boolean deleteUser(User user) throws SQLException;
    boolean updateUser(User user) throws SQLException;
    User getUser(int id)          throws SQLException;
    List<User> listAllUsers()     throws SQLException;

}
