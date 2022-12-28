package website.applicants.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import website.applicants.exceptions.GetEnrolleeException;
import website.applicants.exceptions.SaveException;
import website.applicants.mappers.EnrolleeMapper;
import website.applicants.models.Enrollee;
import website.applicants.repository.EnrolleeRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class DefaultEnrolleeService implements EnrolleeService {
    private final EnrolleeRepository enrolleeRepository;

    @Override
    public List<Enrollee> getAllEnrolles() {
        return EnrolleeMapper.instance
            .listEnrolleeEntityToListEnrollee(StreamSupport
                .stream(enrolleeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList()));
    }

    @Override
    public Enrollee getEnrollee(final int id) throws GetEnrolleeException {
        return EnrolleeMapper.instance.enrolleeEntityToEnrollee(enrolleeRepository.findById(id)
            .orElseThrow(() -> new GetEnrolleeException("Абитуриент не найден")));
    }

    @Override
    public void save(final Enrollee enrollee) throws SaveException {
        try {
            enrolleeRepository.save(EnrolleeMapper.instance.enrolleeToEnrolleeEntity(enrollee));
        } catch (Exception exception) {
            throw new SaveException("Введены не корректные данные");
        }
    }
}
