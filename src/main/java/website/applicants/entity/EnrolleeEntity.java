package website.applicants.entity;

import lombok.*;
import website.applicants.models.Enrollee;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ENROLLEE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnrolleeEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private int id;
    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTHDAY", nullable = false)
    private Date birthday;
    @Column(name = "FULLNAME", length = 64, nullable = false)
    private String fullName;

    public static EnrolleeEntity enrolleeEntity(Enrollee enrollee) {
        return EnrolleeEntity.builder()
                .id(enrollee.getId())
                .birthday(enrollee.getBirthday())
                .fullName(enrollee.getFullName()).build();
    }
}
