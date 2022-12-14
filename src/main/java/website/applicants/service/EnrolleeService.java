package website.applicants.service;

import website.applicants.models.Enrollee;

import java.util.List;

public interface EnrolleeService {
    List<Enrollee> getAllEnrolles();

    Enrollee getEnrollee(int id);

    void save(Enrollee enrollee);
}
