package website.applicants.service;

import website.applicants.dao.Dao;
import website.applicants.entity.EnrolleeEntity;
import website.applicants.models.Enrollee;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class EnrolleeService {
    private Dao enrolleeDao;

    public int sizeEnrollees() {
        return enrolleeDao.size();
    }

    public List<Enrollee> getAllEnrolles() {
        return enrolleeDao.getAll();
    }

    public Enrollee getEnrollee(int id) {
        return new Enrollee((EnrolleeEntity) enrolleeDao.get(id));
    }

    public void save(Enrollee enrollee) {
        enrollee.setId(enrolleeDao.size());
        enrolleeDao.save(new EnrolleeEntity(enrollee));
    }
}
