package website.applicants.dao;

import website.applicants.H2Connection;
import website.applicants.entity.EnrolleeEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static website.applicants.H2Connection.*;

public class EnrolleeDBDao implements Dao<EnrolleeEntity> {

    private final H2Connection h2Connection;
    public EnrolleeDBDao() {
        h2Connection = getH2Connection();
        h2Connection.executeStatement("CREATE TABLE IF NOT EXISTS ENROLLEE" +
                "(id number primary key not null," +
                " birthday date not null, " +
                " fullName varchar(30) not null );");
    }

    @Override
    public int size() {
        ResultSet resultId = h2Connection.executeQueryStatement("SELECT id, FROM ENROLLEE");
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
        ResultSet resultEnrollee = h2Connection.executeQueryStatement("SELECT * FROM ENROLLEE WHERE id = " + id);
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
        ResultSet resultEnrollee = h2Connection.executeQueryStatement("SELECT * FROM ENROLLEE");
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
        h2Connection.executeStatement("INSERT INTO ENROLLEE (id, birthday, fullName)\n" +
                "VALUES (" + enrollee.getId() + ", '" +
                new SimpleDateFormat("yyyy-MM-dd").format(enrollee.getBirthday()) + "', '"
                + enrollee.getFullName() + "');");
    }
}

