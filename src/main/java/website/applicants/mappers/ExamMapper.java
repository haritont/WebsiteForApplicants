package website.applicants.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import website.applicants.entity.ExamEntity;
import website.applicants.models.Exam;

@Mapper(componentModel = "spring")
public interface ExamMapper {
    ExamMapper instance = Mappers.getMapper(ExamMapper.class);

    @Mapping(target = "id", source = "exam.id")
    @Mapping(target = "idEnrollee", source = "exam.idEnrollee")
    @Mapping(target = "subject", source = "exam.subject")
    @Mapping(target = "score", source = "exam.score")
    ExamEntity examToExamEntity(Exam exam);

    @Mapping(target = "id", source = "examEntity.id")
    @Mapping(target = "idEnrollee", source = "examEntity.idEnrollee")
    @Mapping(target = "subject", source = "examEntity.subject")
    @Mapping(target = "score", source = "examEntity.score")
    Exam examEntityToExam(ExamEntity examEntity);
}
