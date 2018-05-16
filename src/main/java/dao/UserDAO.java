package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    public boolean insertUser(User user) throws SQLException;
    public boolean deleteUser(User user) throws SQLException;
    public boolean updateUser(User user) throws SQLException;
    public User getUser(int id)          throws SQLException;
    public List<User> listAllUsers()     throws SQLException;

}
