package website.applicants.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ENROLLEE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EnrolleeEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    int id;
    @Column(name = "FULLNAME", length = 64, nullable = false)
    String fullName;
    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTHDAY", nullable = false)
    Date birthday;
}
