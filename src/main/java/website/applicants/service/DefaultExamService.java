package website.applicants.service;

import com.google.common.collect.ImmutableSet;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;
import website.applicants.entity.ExamEntity;
import website.applicants.models.Exam;
import website.applicants.repository.ExamRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class DefaultExamService implements ExamService {
    private final ExamRepository examRepository;
    private final DozerBeanMapper mapper = new DozerBeanMapper();

    @Override
    public List<Exam> getAllExams() {
        return StreamSupport.stream(examRepository.findAll().spliterator(), false)
                .map(examEntity -> mapper.map(examEntity, Exam.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getSingleExams() {
        return ImmutableSet.copyOf(getAllExams()
                .stream()
                .map(Exam::getSubject)
                .collect(Collectors.toList())).asList();
    }

    @Override
    public void save(final Exam exam) {
        if (!exam.getSubject().isEmpty()) {
            examRepository.save(mapper.map(exam, ExamEntity.class));
        }
    }

    @Override
    public List<Exam> getExamsEnrolleeId(final int id) {
        return examRepository.findAllByIdEnrolleeLike(id).stream()
                .map(examEntity -> mapper.map(examEntity, Exam.class))
                .collect(Collectors.toList());
    }
}
