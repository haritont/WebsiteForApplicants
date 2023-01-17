package website.applicants.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NotNull
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Enrollee {
    int id;
    @Size(min = 2, max = 30)
    String fullName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date birthday;
}
