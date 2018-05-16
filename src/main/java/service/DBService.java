package service;

import dao.UserDAO;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBService {
    public void printConnectInfo() throws SQLException;
    public Connection getConnection();
    public UserDAO getUserDAO();

}
