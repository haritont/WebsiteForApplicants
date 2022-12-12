package website.applicants.dao;

import website.applicants.entity.ExamEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static website.applicants.H2Connection.executeQueryStatement;
import static website.applicants.H2Connection.executeStatement;

public class ExamDBDao implements Dao<ExamEntity> {

    public ExamDBDao() {
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
        for (int index = 1; index <= size(); index++) {
            try {
                resultExam.absolute(index);
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
}
