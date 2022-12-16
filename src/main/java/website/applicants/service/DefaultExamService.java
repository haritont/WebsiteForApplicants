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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultExamService implements ExamService {
    private final ExamRepository examRepository;

    @Override
    public List<Exam> getAllExams() {
        val exams = new ArrayList<Exam>();
        examRepository.findAll().forEach(examEntity -> exams.add(Exam.exam(examEntity)));
        return exams;
    }

    @Override
    public List<String> getSingleExams() {
        val subjects = new ArrayList<String>();
        getAllExams()
                .forEach(exam -> subjects
                        .add(exam.getSubject()));
        return ImmutableSet.copyOf(subjects).asList();
    }

    @Override
    public void save(final Exam exam) {
        if (!exam.getSubject().isEmpty()) {
            examRepository.save(ExamEntity.examEntity(exam));
        }
    }

    @Override
    public List<Exam> getExamsEnrolleeId(final int id) {
        return getAllExams()
                .stream()
                .filter(exam -> exam.getIdEnrollee() == id)
                .collect(Collectors.toList());
    }
}
