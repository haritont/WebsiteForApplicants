package website.applicants.dao;

import website.applicants.models.Enrollee;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class EnrolleeListDao implements Dao<Enrollee> {

    private List<Enrollee> enrollees = new ArrayList<>();
    @Override
    public Enrollee get(int id) {
        return enrollees.get((int)id);
    }

    @Override
    public List<Enrollee> getAll() {
        return enrollees;
    }

    @Override
    public void save(Enrollee enrollee) {
        enrollees.add(enrollee);
    }

    @Override
    public void delete(Enrollee enrollee) {
        enrollees.remove(enrollee);
    }
    public int size() {
        return enrollees.size();
    }
}

