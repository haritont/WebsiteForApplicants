package website.applicants.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import website.applicants.entity.EnrolleeEntity;

@Repository
public interface EnrolleeRepository extends CrudRepository <EnrolleeEntity, Integer>{
}
