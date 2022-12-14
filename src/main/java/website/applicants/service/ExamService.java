package website.applicants.service;

import website.applicants.models.Exam;

import java.util.List;

public interface ExamService {
    List<Exam> getAllExams();

    List<String> getSingleExams();

    void save(Exam exam);

    List<Exam> getExamsEnrolleeId(int id);
}
