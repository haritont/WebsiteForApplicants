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
        val iterable = enrolleeRepository.findAll();
        val enrollees = new ArrayList<Enrollee>();
        for (EnrolleeEntity enrolleeEntity : iterable) {
            enrollees.add(new Enrollee(enrolleeEntity));
        }

        return enrollees;
    }

    @Override
    public Enrollee getEnrollee(int id) {
        return new Enrollee(enrolleeRepository.findById(id).orElseThrow(() -> new NullPointerException("Enrollee not found")));
    }

    @Override
    public void save(Enrollee enrollee) {
        if (!enrollee.getFullName().equals("")) enrolleeRepository.save(new EnrolleeEntity(enrollee));
    }
}
