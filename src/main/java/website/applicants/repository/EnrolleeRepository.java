package website.applicants.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.applicants.entity.EnrolleeEntity;

@Repository
public interface EnrolleeRepository extends JpaRepository<EnrolleeEntity, Integer> {
}
