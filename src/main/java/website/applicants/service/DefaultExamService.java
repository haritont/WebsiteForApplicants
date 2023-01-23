package website.applicants.service;

import com.google.common.collect.ImmutableSet;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import website.applicants.exceptions.SaveException;
import website.applicants.mappers.ExamMapper;
import website.applicants.models.Exam;
import website.applicants.repository.ExamRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DefaultExamService implements ExamService {
    ExamRepository examRepository;
    ExamMapper mapper;

    @Override
    public List<Exam> getAllExams() {
        return mapper.listExamEntityToListExam(new ArrayList<>(examRepository.findAll()));
    }

    @Override
    public List<String> getSingleExams() {
        return ImmutableSet.copyOf(getAllExams()
            .stream()
            .map(Exam::getSubject)
            .collect(Collectors.toList())).asList();
    }

    @Override
    public void save(final Exam exam) throws SaveException {
        try {
            examRepository.save(mapper.examToExamEntity(exam));
        } catch (DataIntegrityViolationException exception) {
            Logger.getLogger(DefaultEnrolleeService.class.getName())
                .info(exception.getMessage());
            throw new SaveException("Введены не корректные данные");
        }
    }

    @Override
    public List<Exam> getExamsEnrolleeId(final int id) {
        return mapper.listExamEntityToListExam(new ArrayList<>(examRepository.findAllByIdEnrolleeLike(id)));
    }
}
