package website.applicants.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "EXAM")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExamEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    int id;
    @Column(name = "IDENROLLEE", nullable = false)
    int idEnrollee;
    @Column(name = "SUBJECT", length = 64, nullable = false)
    String subject;
    @Column(name = "SCORE", nullable = false)
    int score;
}
