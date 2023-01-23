package website.applicants.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.applicants.entity.EnrolleeEntity;

import java.util.List;

@Repository
public interface EnrolleeRepository extends JpaRepository<EnrolleeEntity, Integer> {
    List<EnrolleeEntity> findAllByIdBetween(int firstId, int endId);
}
