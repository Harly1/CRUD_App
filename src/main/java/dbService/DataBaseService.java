package dbService;

import dataSets.UsersDataSet;

import java.sql.Connection;

public interface DataBaseService {
    Connection getSqlConnection();
    void printConnectInfo();
    UsersDataSet getUserById(int id);
    UsersDataSet getUserByLogin(String login);
    int addUser();
    int deleteUser();
}
