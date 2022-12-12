package website.applicants.service;

import website.applicants.dao.ExamDBDao;
import website.applicants.entity.ExamEntity;
import website.applicants.models.Exam;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ExamService {
    private ExamDBDao examDao;

    public List<Exam> getAllExams() {
        List<Exam> exams = new ArrayList<>();
        examDao.getAll().forEach(x -> exams.add(new Exam(x)));
        return exams;
    }

    public void save(Exam exam) {
        examDao.save( new ExamEntity(exam));
    }

    public List<Exam> getExamsEnrolleeId(int id) {
        return getAllExams().stream().filter(x -> x.getIdEnrollee() == id).collect(Collectors.toList());
    }
}
