package website.applicants.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import website.applicants.entity.ExamEntity;

import java.util.List;

@Repository
public interface ExamRepository extends CrudRepository<ExamEntity, Integer> {
    List<ExamEntity> findAllByIdEnrolleeLike(int id);
}
