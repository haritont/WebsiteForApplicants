package website.applicants.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import website.applicants.entity.ExamEntity;
@Repository
public interface ExamRepository extends CrudRepository<ExamEntity, Integer> {
}
