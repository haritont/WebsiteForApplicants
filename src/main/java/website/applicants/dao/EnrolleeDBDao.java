package website.applicants.dao;

import website.applicants.entity.EnrolleeEntity;
import website.applicants.H2Connection;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EnrolleeDBDao implements Dao<EnrolleeEntity> {
    private H2Connection h2Connection;

    public EnrolleeDBDao() {
        h2Connection = H2Connection.getH2Connection();
        try {
            Statement  statement = h2Connection.getConnection().createStatement();
            String createTableEnrollee = "CREATE TABLE IF NOT EXISTS ENROLLEE" +
                    "(id number primary key not null," +
                    " birthday date not null, " +
                    " fullName varchar(30) not null );" +
                    "DELETE FROM ENROLLEE WHERE ID = 0; " +
                    "INSERT INTO ENROLLEE (id, birthday, fullName)\n" +
                    "VALUES (0, '2003-03-02', 'Никита Шалопаев');" +
                    "DELETE FROM ENROLLEE WHERE ID = 1; INSERT INTO ENROLLEE (id, birthday, fullName)\n" +
                    "VALUES (1, '2005-02-12', 'Харитон Обжоркин');";
            statement.execute(createTableEnrollee);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int size() {
        try {
            Statement  statement = h2Connection.getConnection().createStatement();
            String sql = "SELECT id, FROM ENROLLEE";
            ResultSet resultId =  statement.executeQuery(sql);
            resultId.last();
            return resultId.getRow();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EnrolleeEntity get(int id) {
        try {
            EnrolleeEntity enrolleeEntity = new EnrolleeEntity();
            Statement  statement = h2Connection.getConnection().createStatement();
            String sql = "SELECT * FROM ENROLLEE WHERE id = "+ id;
            ResultSet resultEnrolee =  statement.executeQuery(sql);
            while (resultEnrolee.next()) {
                String fullName = resultEnrolee.getString("fullName");
                Date birthday = resultEnrolee.getDate("birthday");

                enrolleeEntity.setId(id);
                enrolleeEntity.setFullName(fullName);
                enrolleeEntity.setBirthday(birthday);
            }
            statement.close();
            return enrolleeEntity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<EnrolleeEntity> getAll() {
        try {
            List<EnrolleeEntity> enrolleeEntities = new ArrayList<>();
            Statement statement = h2Connection.getConnection().createStatement();
            ResultSet resultSet = statement.
                    executeQuery("select * from ENROLLEE");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String fullName = resultSet.getString("fullName");
                Date birthday = resultSet.getDate("birthday");
                EnrolleeEntity enrolleeEntity = new EnrolleeEntity();
                enrolleeEntity.setId(id);
                enrolleeEntity.setFullName(fullName);
                enrolleeEntity.setBirthday(birthday);
                enrolleeEntities.add(enrolleeEntity);
            }
            statement.close();
            return enrolleeEntities;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(EnrolleeEntity enrollee) {
        try {
            Statement statement = h2Connection.
                    getConnection().
                    createStatement();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            String insert = "INSERT INTO ENROLLEE (id, birthday, fullName)\n" +
                    "VALUES ("+enrollee.getId()+", '"+dateFormat.format(enrollee.getBirthday())+"', '"
                    +enrollee.getFullName()+"');";
            statement.execute(insert);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(EnrolleeEntity enrollee) {
        try {
            Statement statement = h2Connection.
                    getConnection().
                    createStatement();
            String delete = "DELETE FROM ENROLLEE " + "WHERE id = "+enrollee.getId();
            statement.execute(delete);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

