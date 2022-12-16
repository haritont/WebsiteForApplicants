package website.applicants.models;

import lombok.*;
import website.applicants.entity.ExamEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Exam {
    private int idEnrollee;
    private String subject;
    private int score;

    public static Exam exam(ExamEntity exam) {
        return Exam.builder()
                .idEnrollee(exam.getIdEnrollee())
                .score(exam.getScore())
                .subject(exam.getSubject())
                .build();
    }
}
