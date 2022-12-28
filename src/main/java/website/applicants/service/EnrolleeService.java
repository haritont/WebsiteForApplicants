package website.applicants.service;

import website.applicants.exceptions.GetEnrolleeException;
import website.applicants.exceptions.SaveException;
import website.applicants.models.Enrollee;

import java.util.List;

public interface EnrolleeService {
    List<Enrollee> getAllEnrolles();

    Enrollee getEnrollee(int id) throws GetEnrolleeException;

    void save(Enrollee enrollee) throws SaveException;
}
