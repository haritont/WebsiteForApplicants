package website.applicants.entity;

import lombok.*;

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
    @Column(name = "ID", nullable = false)
    private int id;
    @Column(name = "IDENROLLEE", nullable = false)
    private int idEnrollee;
    @Column(name = "SUBJECT", length = 64, nullable = false)
    private String subject;
    @Column(name = "SCORE", nullable = false)
    private int score;
}
