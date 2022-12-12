package website.applicants.dao;

import website.applicants.entity.EnrolleeEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static website.applicants.H2Connection.executeQueryStatement;
import static website.applicants.H2Connection.executeStatement;

public class EnrolleeDBDao implements Dao<EnrolleeEntity> {

    public EnrolleeDBDao() {
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
            return new EnrolleeEntity(resultEnrollee.getInt("id"), resultEnrollee.getDate("birthday"),
                    resultEnrollee.getString("fullName"));

        } catch (SQLException exp) {
            throw new RuntimeException(exp);
        }
    }

    @Override
    public List<EnrolleeEntity> getAll() {
        List<EnrolleeEntity> enrolleeEntities = new ArrayList<>();
        ResultSet resultEnrollee = executeQueryStatement("SELECT * FROM ENROLLEE");
        for (int index = 1; index <= size(); index++) {
            try {
                resultEnrollee.absolute(index);
            } catch (SQLException exp) {
                throw new RuntimeException(exp);
            }
            try {
                enrolleeEntities.add(new EnrolleeEntity(resultEnrollee.getInt("id"),
                        resultEnrollee.getDate("birthday"),
                        resultEnrollee.getString("fullName")));
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
}

