package website.applicants.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
        return StreamSupport.stream(enrolleeRepository.findAll().spliterator(), false)
            .map(EnrolleeMapper.instance::enrolleeEntityToEnrollee)
            .collect(Collectors.toList());
    }

    @Override
    public Enrollee getEnrollee(final int id) {
        return EnrolleeMapper.instance.enrolleeEntityToEnrollee(enrolleeRepository.findById(id)
            .orElseThrow(() -> new NullPointerException("Абитуриент не найден")));
    }

    @Override
    public void save(final Enrollee enrollee) {
        if (!enrollee.getFullName().isEmpty() && enrollee.getBirthday() != null) {
            enrolleeRepository.save(EnrolleeMapper.instance.enrolleeToEnrolleeEntity(enrollee));
        }
    }
}
