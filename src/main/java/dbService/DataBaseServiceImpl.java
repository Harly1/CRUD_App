package dbService;

import dao.UserDAO;
import dataSets.UsersDataSet;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseServiceImpl implements DataBaseService {

    private final Connection connection;

    public DataBaseServiceImpl() {
        this.connection = getMySqlConnection();
    }

    public UsersDataSet getUser(long id) throws DBException {
        try {
            return (new UserDAO(connection).get(id));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
    public String getUser(String login) throws DBException {
        try {

            return (new UserDAO(connection).getUserLogin(login));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public String getUserPassword(String password) throws DBException {
        try {

            return (new UserDAO(connection).getUserPassword(password));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public long addUser(String login, String password) throws DBException {
        try {
            connection.setAutoCommit(false);
            UserDAO dao = new UserDAO(connection);
//            dao.dropTable();
            dao.createTable();
            dao.insertUserLoginPassword(login,password);
            connection.commit();
            return dao.getUserId(login);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    public void cleanUp() throws DBException {
        UserDAO dao = new UserDAO(connection);
        try {
            dao.dropTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void printConnectInfo() {
        try {
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    public static Connection getSqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_example?").          //db name
                    append("user=tully&").          //login
                    append("password=tully");       //password

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*    public static Connection getH2Connection() {
            try {
                String url = "jdbc:h2:./h2db";
                String name = "tully";
                String pass = "tully";

                JdbcDataSource ds = new JdbcDataSource();
                ds.setURL(url);
                ds.setUser(name);
                ds.setPassword(pass);

                Connection connection = DriverManager.getConnection(url, name, pass);
                return connection;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }*/
    public static Connection getMySqlConnection() {
        try {
            String url = "jdbc:sqlserver://localhost:56342;database=Test";
            String login = "ENTERPRISE\\krvl8001";
            String password = "ACNielsen23";

            JdbcDataSource jdbcDataSource = new JdbcDataSource();
            jdbcDataSource.setURL(url);
            jdbcDataSource.setUser(login);
            jdbcDataSource.setPassword(password);

            Connection connection = DriverManager.getConnection(url, login, password);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UsersDataSet getUserById(int id) {
        return null;
    }

    @Override
    public UsersDataSet getUserByLogin(String login) {
        return null;
    }

    @Override
    public int addUser() {
        return 0;
    }

    @Override
    public int deleteUser() {
        return 0;
    }
}
