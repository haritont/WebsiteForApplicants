package website.applicants.service;

import com.google.common.collect.ImmutableSet;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.dozer.DozerBeanMapper;
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
    private final DozerBeanMapper mapper = new DozerBeanMapper();

    @Override
    public List<Exam> getAllExams() {
        val exams = new ArrayList<Exam>();
        examRepository.findAll()
                .forEach(examEntity -> exams
                        .add(mapper.map(examEntity, Exam.class)));
        return exams;
    }

    @Override
    public List<String> getSingleExams() {
        val subjects = new ArrayList<String>();
        getAllExams().forEach(exam -> subjects
                .add(exam.getSubject()));
        return ImmutableSet.copyOf(subjects).asList();
    }

    @Override
    public void save(final Exam exam) {
        if (!exam.getSubject().isEmpty()) {
            examRepository.save(mapper.map(exam, ExamEntity.class));
        }
    }

    @Override
    public List<Exam> getExamsEnrolleeId(final int id) {
        val exams = new ArrayList<Exam>();
        examRepository
                .findAllByIdEnrolleeLike(id)
                .forEach(examEntity -> exams
                        .add(mapper.map(examEntity, Exam.class)));
        return exams;
    }
}
