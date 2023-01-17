package website.applicants.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import website.applicants.entity.ExamEntity;
import website.applicants.models.Exam;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExamMapper {

    @Mapping(target = "id", source = "exam.id")
    @Mapping(target = "idEnrollee", source = "exam.idEnrollee")
    @Mapping(target = "subject", source = "exam.subject")
    @Mapping(target = "score", source = "exam.score")
    ExamEntity examToExamEntity(Exam exam);

    List<Exam> listExamEntityToListExam(List<ExamEntity> examEntities);
}
