package website.applicants.dao;

import website.applicants.models.Exam;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
public class ExamListDao implements Dao<Exam> {
    private List<Exam> exams = new ArrayList<>();

    @Override
    public int size() {
        return exams.size();
    }

    @Override
    public Exam get(int id) {
        return exams.get(id);
    }

    @Override
    public List<Exam> getAll() {
        return exams;
    }

    @Override
    public void save(Exam exam) {
        exams.add(exam);
    }

    @Override
    public void delete(Exam exam) {
        exams.remove(exam);
    }
}
