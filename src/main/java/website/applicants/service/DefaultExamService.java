package website.applicants.service;

import com.google.common.collect.ImmutableSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import website.applicants.mappers.ExamMapper;
import website.applicants.models.Exam;
import website.applicants.repository.ExamRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class DefaultExamService implements ExamService {
    private final ExamRepository examRepository;

    @Override
    public List<Exam> getAllExams() {
        return StreamSupport.stream(examRepository.findAll().spliterator(), false)
            .map(ExamMapper.instance::examEntityToExam)
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
            examRepository.save(ExamMapper.instance.examToExamEntity(exam));
        }
    }

    @Override
    public List<Exam> getExamsEnrolleeId(final int id) {
        return examRepository.findAllByIdEnrolleeLike(id).stream()
            .map(ExamMapper.instance::examEntityToExam)
            .collect(Collectors.toList());
    }
}
