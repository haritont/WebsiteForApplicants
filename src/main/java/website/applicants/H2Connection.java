package website.applicants;

import lombok.Getter;

import java.sql.*;

public class H2Connection {
    private static final String JDBC_URL = "jdbc:h2:mem:testdb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final String DRIVER_CLASS = "org.h2.Driver";

    @Getter
    Connection connection;

    private H2Connection()  {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static H2Connection getH2Connection()  {
        return new H2Connection();
    }

    public void executeStatement(String request){
        Statement statement = getStatement();
        try {
            statement.execute(request);
        } catch (SQLException exp) {
            throw new RuntimeException(exp);
        }
        try {
            statement.close();
        } catch (SQLException exp) {
            throw new RuntimeException(exp);
        }
    }
    public ResultSet executeQueryStatement(String request){
        Statement  statement = getStatement();
        try {
            return statement.executeQuery(request);
        } catch (SQLException exp) {
            throw new RuntimeException(exp);
        }
    }
    private Statement getStatement(){
        try {
            return getConnection().createStatement();
        } catch (SQLException exp) {
            throw new RuntimeException(exp);
        }
    }
}
