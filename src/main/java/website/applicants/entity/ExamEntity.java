package website.applicants.entity;

import lombok.*;
import website.applicants.models.Exam;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "EXAM")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private int id;
    @Column(name = "IDENROLLEE", nullable = false)
    private int idEnrollee;

    @Column(name = "SUBJECT", length = 64, nullable = false)
    private String subject;

    @Column(name = "SCORE", nullable = false)
    private int score;

    public static ExamEntity examEntity(Exam exam) {
        return ExamEntity.builder()
                .idEnrollee(exam.getIdEnrollee())
                .score(exam.getScore())
                .subject(exam.getSubject())
                .build();
    }
}
