package website.applicants.service;

import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;
import website.applicants.entity.EnrolleeEntity;
import website.applicants.models.Enrollee;
import website.applicants.repository.EnrolleeRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class DefaultEnrolleeService implements EnrolleeService {
    private final EnrolleeRepository enrolleeRepository;
    private final DozerBeanMapper mapper = new DozerBeanMapper();

    @Override
    public List<Enrollee> getAllEnrolles() {
        return StreamSupport.stream(enrolleeRepository.findAll().spliterator(), false)
                .map(enrolleeEntity -> mapper.map(enrolleeEntity, Enrollee.class))
                .collect(Collectors.toList());
    }

    @Override
    public Enrollee getEnrollee(final int id) {
        return mapper.map(enrolleeRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Абитуриент не найден")), Enrollee.class);
    }

    @Override
    public void save(final Enrollee enrollee) {
        if (!enrollee.getFullName().isEmpty() && enrollee.getBirthday() != null) {
            enrolleeRepository
                    .save(mapper.map(enrollee, EnrolleeEntity.class));
        }
    }
}
