package website.applicants.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Exam {
    int id;
    @NonNull
    Integer idEnrollee;
    String subject;
    int score;
}
