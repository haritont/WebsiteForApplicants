package website.applicants.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import website.applicants.entity.EnrolleeEntity;
import website.applicants.models.Enrollee;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnrolleeMapper {

    @Mapping(target = "id", source = "enrollee.id")
    @Mapping(target = "fullName", source = "enrollee.fullName")
    @Mapping(target = "birthday", source = "enrollee.birthday")
    EnrolleeEntity enrolleeToEnrolleeEntity(Enrollee enrollee);

    @Mapping(target = "id", source = "enrolleeEntity.id")
    @Mapping(target = "fullName", source = "enrolleeEntity.fullName")
    @Mapping(target = "birthday", source = "enrolleeEntity.birthday")
    Enrollee enrolleeEntityToEnrollee(EnrolleeEntity enrolleeEntity);

    List<Enrollee> listEnrolleeEntityToListEnrollee(List<EnrolleeEntity> enrolleeEntities);
}
