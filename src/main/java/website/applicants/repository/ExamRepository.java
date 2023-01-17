package website.applicants.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.applicants.entity.ExamEntity;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<ExamEntity, Integer> {
    List<ExamEntity> findAllByIdEnrolleeLike(int id);
}
