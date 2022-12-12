package website.applicants.dao;

import website.applicants.entity.EnrolleeEntity;
import website.applicants.H2Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EnrolleeDBDao implements Dao<EnrolleeEntity> {
    private final H2Connection h2Connection;

    public EnrolleeDBDao() {
        h2Connection = H2Connection.getH2Connection();
        executeStatement("CREATE TABLE IF NOT EXISTS ENROLLEE" +
                "(id number primary key not null," +
                " birthday date not null, " +
                " fullName varchar(30) not null );");
    }

    @Override
    public int size() {
        ResultSet resultId = executeQueryStatement("SELECT id, FROM ENROLLEE");
        try {
            resultId.last();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            return resultId.getRow();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EnrolleeEntity get(int id) {
        ResultSet resultEnrollee = executeQueryStatement("SELECT * FROM ENROLLEE WHERE id = " + id);
        try {
            resultEnrollee.next();
            return new EnrolleeEntity(resultEnrollee.getInt("id"), resultEnrollee.getDate("birthday")
                    , resultEnrollee.getString("fullName"));

        } catch (SQLException exp) {
            throw new RuntimeException(exp);
        }
    }

    @Override
    public List<EnrolleeEntity> getAll() {
        List<EnrolleeEntity> enrolleeEntities = new ArrayList<>();
        ResultSet resultSet = executeQueryStatement("SELECT * FROM ENROLLEE");
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException exp) {
                throw new RuntimeException(exp);
            }
            try {
                enrolleeEntities.add(new EnrolleeEntity(resultSet.getInt("id"),resultSet.getDate("birthday")
                        ,resultSet.getString("fullName")));
            } catch (SQLException exp) {
                throw new RuntimeException(exp);
            }
        }
        return enrolleeEntities;
    }

    @Override
    public void save(EnrolleeEntity enrollee) {
        executeStatement("INSERT INTO ENROLLEE (id, birthday, fullName)\n" +
                "VALUES (" + enrollee.getId() + ", '" +
                new SimpleDateFormat("yyyy-MM-dd").format(enrollee.getBirthday()) + "', '"
                + enrollee.getFullName() + "');");
    }

    private void executeStatement(String request){
        Statement  statement = getStatement();
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
    private ResultSet executeQueryStatement(String request){
        Statement  statement = getStatement();
        try {
            return statement.executeQuery(request);
        } catch (SQLException exp) {
            throw new RuntimeException(exp);
        }
    }
    private Statement getStatement(){
        try {
            return h2Connection.getConnection().createStatement();
        } catch (SQLException exp) {
            throw new RuntimeException(exp);
        }
    }
}

