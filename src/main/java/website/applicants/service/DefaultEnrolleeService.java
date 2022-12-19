package website.applicants.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import website.applicants.entity.EnrolleeEntity;
import website.applicants.models.Enrollee;
import website.applicants.repository.EnrolleeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultEnrolleeService implements EnrolleeService {
    private final EnrolleeRepository enrolleeRepository;

    @Override
    public List<Enrollee> getAllEnrolles() {
        val enrollees = new ArrayList<Enrollee>();
        enrolleeRepository.findAll()
                .forEach(enrolleeEntity -> enrollees
                        .add(Enrollee.enrollee(enrolleeEntity)));
        return enrollees;
    }

    @Override
    public Enrollee getEnrollee(final int id) {
        return Enrollee.enrollee(enrolleeRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Абитуриент не найден")));
    }

    @Override
    public void save(final Enrollee enrollee) {
        if (!enrollee.getFullName().isEmpty() && enrollee.getBirthday() != null) {
            enrolleeRepository
                    .save(EnrolleeEntity.enrolleeEntity(enrollee));
        }
    }
}
