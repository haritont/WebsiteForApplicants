package website.applicants.dao;

import website.applicants.entity.ExamEntity;
import website.applicants.H2Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ExamDBDao implements Dao<ExamEntity> {

    private final H2Connection h2Connection;

    public ExamDBDao(){
        h2Connection = H2Connection.getH2Connection();
        try {
            Statement statement = h2Connection.getConnection().createStatement();
            String createTableExam = "CREATE TABLE IF NOT EXISTS EXAM" +
                    "(idEnrollee number primary key not null," +
                    " score number not null, " +
                    " subject varchar(30) not null );" +
                    "INSERT INTO EXAM (id, idEnrollee, score, subject)\n" +
                    "VALUES (0, 0, 70, 'Шалопайство');" +
                    "INSERT INTO EXAM (id, idEnrollee, score, subject)\n" +
                    "VALUES (1, 1, 100 , 'Буянство');"+
                    "INSERT INTO EXAM (id, idEnrollee, score, subject)\n" +
                    "VALUES (2, 0, 100 , 'Хулиганство');"+
                    "INSERT INTO EXAM (id, idEnrollee, score, subject)\n" +
                    "VALUES (3, 1, 100 , 'Обжорство');";
            statement.execute(createTableExam);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public int size() {
        try {
            Statement  statement = h2Connection.getConnection().createStatement();
            String sql = "SELECT idEnrollee, FROM EXAM";
            ResultSet resultId =  statement.executeQuery(sql);
            resultId.last();
            return resultId.getRow();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ExamEntity get(int idEnrollee) {
        try {
            Statement  statement = h2Connection.getConnection().createStatement();
            String sql = "SELECT * FROM EXAM WHERE idEnrollee = "+ idEnrollee;
            ResultSet resultExam =  statement.executeQuery(sql);
            ExamEntity examEntity = null;
            while (resultExam.next()) {
                String subject = resultExam.getString("subject");
                int score = resultExam.getInt("score");
                int id =resultExam.getInt("id");
                examEntity = new ExamEntity(id, idEnrollee, subject, score);
            }
            return examEntity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List getAll() {
        List<ExamEntity> examEntities = new ArrayList<>();
        try {
            Statement statement = h2Connection.getConnection().createStatement();
            ResultSet resultSet = statement.
                    executeQuery("select * from EXAM");
            while (resultSet.next()) {
                int idEnrollee = resultSet.getInt("idEnrollee");
                String subject = resultSet.getString("subject");
                int score = resultSet.getInt("score");
                int id =resultSet.getInt("id");
                examEntities.add(new ExamEntity(id, idEnrollee, subject, score));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return examEntities;
    }
    @Override
    public void save(ExamEntity examEntity) {
        try {
            Statement statement = h2Connection.
                    getConnection().
                    createStatement();
            ResultSet resultId = statement.
                    executeQuery("select * from EXAM where idEnrollee = "+examEntity.getIdEnrollee());
            Boolean checkExam =false;
            while (checkExam==false && resultId.next()){
                if (resultId.getString("subject").equals(examEntity.getSubject())) {
                    checkExam = true;
                    int id = resultId.getInt("id");
                    String update = "UPDATE EXAM SET score = " + examEntity.getScore() +
                            " WHERE id = " + id+";";
                    statement.execute(update);
                }
            }
            if (checkExam==false) {
                String insert = "INSERT INTO EXAM (id, idEnrollee, score, subject)\n" +
                        "VALUES (" + size() + "," + examEntity.getIdEnrollee() + "," + examEntity.getScore() + ", '" + examEntity.getSubject() + "');";
                statement.execute(insert);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(ExamEntity examEntity) {
        try {
            Statement statement = h2Connection.
                    getConnection().
                    createStatement();
            String delete = "DELETE FROM EXAM " + "WHERE idEnrollee = "+examEntity.getIdEnrollee();
            statement.execute(delete);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
