package website.applicants.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ENROLLEE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnrolleeEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private int id;
    @Column(name = "FULLNAME", length = 64, nullable = false)
    private String fullName;
    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTHDAY", nullable = false)
    private Date birthday;
}
