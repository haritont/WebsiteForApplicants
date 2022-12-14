package website.applicants.entity;

import website.applicants.models.Exam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "EXAM")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExamEntity {
    @Id
    @GeneratedValue
    @Autowired
    @Column(name = "ID", nullable = false)
    private int id;
    @Column(name = "IDENROLLEE", nullable = false)
    private int idEnrollee;

    @Column(name = "SUBJECT", length = 64, nullable = false)
    private String subject;

    @Column(name = "SCORE", nullable = false)
    private int score;

    public ExamEntity(Exam exam) {
        this.idEnrollee = exam.getIdEnrollee();
        this.subject = exam.getSubject();
        this.score = exam.getScore();
    }
}
