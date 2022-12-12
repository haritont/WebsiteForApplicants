package website.applicants.service;

import website.applicants.dao.ExamDBDao;
import website.applicants.entity.ExamEntity;
import website.applicants.models.Exam;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ExamService {
    private ExamDBDao examDao;

    public long sizeExams() {
        return examDao.size();
    }

    public List<Exam> getAllExams() {
        List<Exam> exams = new ArrayList<>();
        List<ExamEntity> examEntities = examDao.getAll();
        examEntities.stream().forEach(x -> exams.add(new Exam(x)));
        return exams;
    }

    public Exam getExam(int idEnrollee) {
        return new Exam (examDao.get(idEnrollee));
    }

    public void save(Exam exam) {
        ExamEntity examEntity = new ExamEntity(exam);
        examDao.save(examEntity);
    }

    public List<Exam> getExams(List<ExamEntity> examEntitys) {
        List<Exam> exams = new ArrayList<>();
        for (ExamEntity examEntity : examEntitys) {
            exams.add(new Exam(examEntity));
        }
        return exams;
    }
}
