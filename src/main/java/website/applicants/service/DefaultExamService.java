package website.applicants.service;

import com.google.common.collect.ImmutableSet;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import website.applicants.entity.ExamEntity;
import website.applicants.models.Exam;
import website.applicants.repository.ExamRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultExamService implements ExamService {
    private final ExamRepository examRepository;

    @Override
    public List<Exam> getAllExams() {
        val iterable = examRepository.findAll();
        val exams = new ArrayList<Exam>();
        for (ExamEntity examEntity : iterable) {
            exams.add(new Exam(examEntity));
        }
        return exams;
    }

    @Override
    public List<String> getSingleExams() {
        val subjects = new ArrayList<String>();
        val exams = getAllExams();
        for (val exam : exams) {
            subjects.add(exam.getSubject());
        }
        return ImmutableSet.copyOf(subjects).asList();
    }

    @Override
    public void save(Exam exam) {
        if (!exam.getSubject().equals("")) examRepository.save(new ExamEntity(exam));
    }

    @Override
    public List<Exam> getExamsEnrolleeId(int id) {
        val iterable = examRepository.findAll();
        val exams = new ArrayList<Exam>();
        for (ExamEntity examEntity : iterable) {
            if (examEntity.getIdEnrollee() == id) exams.add(new Exam(examEntity));
        }
        return exams;
    }
}
