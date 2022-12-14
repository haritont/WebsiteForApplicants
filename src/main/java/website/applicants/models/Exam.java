package website.applicants.models;

import website.applicants.entity.ExamEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Exam {
    private int idEnrollee;
    private String subject;
    private int score;

    public Exam(ExamEntity exam) {
        this.idEnrollee = exam.getIdEnrollee();
        this.score = exam.getScore();
        this.subject = exam.getSubject();
    }
}
