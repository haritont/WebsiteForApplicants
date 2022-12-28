package website.applicants.service;

import com.google.common.collect.ImmutableSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import website.applicants.mappers.ExamMapper;
import website.applicants.models.Exam;
import website.applicants.repository.ExamRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class DefaultExamService implements ExamService {
    private final ExamRepository examRepository;

    @Override
    public List<Exam> getAllExams() {
        return ExamMapper.instance
            .listExamEntityToListExam(StreamSupport
                .stream(examRepository.findAll().spliterator(), false)
                .collect(Collectors.toList()));
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
        try {
            examRepository.save(ExamMapper.instance.examToExamEntity(exam));
        } catch (Exception exception) {
            throw new IllegalArgumentException("Введены не корректные данные");
        }
    }

    @Override
    public List<Exam> getExamsEnrolleeId(final int id) {
        return ExamMapper.instance
            .listExamEntityToListExam(new ArrayList<>(examRepository.findAllByIdEnrolleeLike(id)));
    }
}
