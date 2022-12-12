package website.applicants.entity;

import website.applicants.models.Enrollee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

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
    @Autowired
    @Column(name = "ID", nullable = false)
    private int id;
    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTHDAY", nullable = false)
    private Date birthday;
    @Column(name = "FULLNAME", length = 64, nullable = false)
    private String fullName;

    public EnrolleeEntity(Enrollee enrollee) {
        this.id = enrollee.getId();
        this.birthday = enrollee.getBirthday();
        this.fullName = enrollee.getFullName();
    }
}
