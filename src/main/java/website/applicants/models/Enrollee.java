package website.applicants.models;

import lombok.*;
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
    private int id;
    @Size(min = 2, max = 30)
    private String fullName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
}
