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

    public ExamDBDao() {
        h2Connection = H2Connection.getH2Connection();
        executeStatement("CREATE TABLE IF NOT EXISTS EXAM" +
                "(id number primary key not null," +
                "idEnrollee number not null," +
                " score number not null, " +
                " subject varchar(30) not null );");
        setExams();
    }

    @Override
    public int size() {
        ResultSet resultId = executeQueryStatement("SELECT idEnrollee, FROM EXAM");
        try {
            resultId.last();
        } catch (SQLException exp) {
            throw new RuntimeException(exp);
        }
        try {
            return resultId.getRow();
        } catch (SQLException exp) {
            throw new RuntimeException(exp);
        }
    }

    @Override
    public ExamEntity get(int idEnrollee) {
        ResultSet resultExam = executeQueryStatement("SELECT * FROM EXAM WHERE idEnrollee = " + idEnrollee);
        try {
            return new ExamEntity(resultExam.getInt("id"), idEnrollee,
                    resultExam.getString("subject"), resultExam.getInt("score"));
        } catch (SQLException exp) {
            throw new RuntimeException(exp);
        }
    }

    @Override
    public List<ExamEntity> getAll() {
        List<ExamEntity> examEntities = new ArrayList<>();
        ResultSet resultExam = executeQueryStatement("select * from EXAM");
        while (true) {
            try {
                if (!resultExam.next()) break;
            } catch (SQLException exp) {
                throw new RuntimeException(exp);
            }
            try {
                examEntities.add(new ExamEntity(resultExam.getInt("id"), resultExam.getInt("idEnrollee"),
                        resultExam.getString("subject"), resultExam.getInt("score")));
            } catch (SQLException exp) {
                throw new RuntimeException(exp);
            }
        }
        return examEntities;
    }


    @Override
    public void save(ExamEntity examEntity) {
        ResultSet resultId = executeQueryStatement("SELECT id FROM EXAM WHERE idEnrollee = "
                + examEntity.getIdEnrollee() + " AND subject = '" + examEntity.getSubject()+"';");
        try {
            resultId.last();
            if (resultId.getRow()!=0) {
                executeStatement("UPDATE EXAM SET score = " + examEntity.getScore() +
                        " WHERE idEnrollee = " + examEntity.getIdEnrollee()
                        + " AND subject = '" + examEntity.getSubject()+"';");
            } else {
                executeStatement("INSERT INTO EXAM (id, idEnrollee, score, subject)\n" +
                        "VALUES (" + size() + "," + examEntity.getIdEnrollee() + "," + examEntity.getScore()
                        + ", '" + examEntity.getSubject() + "');");
            }
        } catch (SQLException exp) {
            throw new RuntimeException(exp);
        }
    }

    private  void setExams(){
        executeStatement("""
                INSERT INTO EXAM (id, idEnrollee, score, subject)
                VALUES (0, 0, 100, 'Шалопайство');INSERT INTO EXAM (id, idEnrollee, score, subject)
                VALUES (1, 0, 100 , 'Буянство');INSERT INTO EXAM (id, idEnrollee, score, subject)
                VALUES (2, 0, 100 , 'Хулиганство');INSERT INTO EXAM (id, idEnrollee, score, subject)
                VALUES (3, 0, 100 , 'Обжорство');""");
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
