package service;

import dao.UserDAO;
import dao.UserDAOImp;
import util.DbHelper;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class DBServiceImp implements DBService {

    @Override
    public void printConnectInfo() throws SQLException {
        Connection jdbcConnection =  new DbHelper().getJdbcConnection();
        DatabaseMetaData dmd = jdbcConnection.getMetaData();
        String url = dmd.getURL();
        String user = dmd.getUserName();
        String dbName = dmd.getDatabaseProductName();
        System.out.println("url= "+url+ " user= "+user+" database= "+dbName);
    }


    public Connection getConnection() {
        return new DbHelper().getJdbcConnection();
    }

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImp(new DbHelper().getJdbcConnection());
    }

    @Override
    public void listUser() {

    }

    @Override
    public void deleteUser() {

    }

    @Override
    public void editUser() {

    }

    @Override
    public void insertUser() {

    }

    @Override
    public void newUser() {

    }

    @Override
    public void updateUser() {

    }
}
