package website.applicants.models;

import website.applicants.entity.EnrolleeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NotNull
public class Enrollee {
    @Size(min=2, max=30)
    private String fullName;
    private int id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    public Enrollee (EnrolleeEntity enrollee){
        this.id=enrollee.getId();
        this.birthday=enrollee.getBirthday();
        this.fullName=enrollee.getFullName();
    }
}
