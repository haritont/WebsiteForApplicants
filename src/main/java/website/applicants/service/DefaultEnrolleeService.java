package website.applicants.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import website.applicants.exceptions.GetEnrolleeException;
import website.applicants.exceptions.SaveException;
import website.applicants.mappers.EnrolleeMapper;
import website.applicants.models.Enrollee;
import website.applicants.repository.EnrolleeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DefaultEnrolleeService implements EnrolleeService {
    EnrolleeRepository enrolleeRepository;
    EnrolleeMapper mapper;

    @Override
    public List<Enrollee> getAllEnrolles(int idFirst, int idEnd) {
        return mapper.listEnrolleeEntityToListEnrollee(new ArrayList<>(enrolleeRepository.findAllByIdBetween(idFirst, idEnd)));
    }

    @Override
    public Enrollee getEnrollee(final int id) throws GetEnrolleeException {
        return mapper.enrolleeEntityToEnrollee(enrolleeRepository.findById(id)
            .orElseThrow(() -> new GetEnrolleeException("Абитуриент не найден")));
    }

    @Override
    public void save(final Enrollee enrollee) throws SaveException {
        try {
            enrolleeRepository.save(mapper.enrolleeToEnrolleeEntity(enrollee));
        } catch (DataIntegrityViolationException exception) {
            Logger.getLogger(DefaultEnrolleeService.class.getName())
                .info(exception.getMessage());
            throw new SaveException("Введены не корректные данные");
        }
    }
}
