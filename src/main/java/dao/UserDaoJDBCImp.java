package dao;


import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImp implements UserDAO {
    private Connection jdbcConnection;

    public UserDaoJDBCImp(Connection jdbcConnection) {

        this.jdbcConnection = jdbcConnection;

    }

    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public boolean insertUser(User user) throws SQLException {
//        String sql = "INSERT INTO users (id, user_name, user_password) VALUES (?, ?, ?)";
        String sql = "INSERT INTO users (user_name, user_password) VALUES (?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, user.getName());
        statement.setString(2, user.getPassword());
//        statement.setString(1, String.valueOf(user.getId()));


        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
//        disconnect();
        return rowInserted;
    }

    public List<User> listAllUsers() throws SQLException {
        List<User> listUser = new ArrayList<>();

        String sql = "SELECT * FROM users";
        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("user_name");
            String password = resultSet.getString("user_password");
            String role = resultSet.getString("user_role");


            User user = new User(id, name, password, role);
            listUser.add(user);
        }

        resultSet.close();
        statement.close();

//        disconnect();

        return listUser;
    }

    public boolean deleteUser(User user) throws SQLException {
        String sql = "DELETE FROM users where id = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, user.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
//        disconnect();
        return rowDeleted;
    }

    public boolean updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET user_name = ?, user_password = ?";
        sql += " WHERE id = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(3, user.getId());
        statement.setString(1, user.getName());
        statement.setString(2, user.getPassword());


        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
//        disconnect();
        return rowUpdated;
    }

    public User getUser(int id) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM users WHERE id = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String name = resultSet.getString("user_name");
            String password = resultSet.getString("user_password");

            user = new User(id, name, password);
        }

        resultSet.close();
        statement.close();

        return user;
    }

    @Override
    public User getUserByNameAndPassword(String name, String password) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM users WHERE user_name = ? AND user_password = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1,name );
        statement.setString(2, password);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
             name = resultSet.getString("user_name");
             password = resultSet.getString("user_password");
             int id = resultSet.getInt("id");
             String role = resultSet.getString("user_role");

            user = new User(id, name, password, role);
        }

        resultSet.close();
        statement.close();

        return user;
    }
}
