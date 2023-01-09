package website.applicants.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Exam {
    private int id;
    @NonNull
    private Integer idEnrollee;
    private String subject;
    private int score;
}
