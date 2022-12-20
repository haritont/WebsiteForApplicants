package website.applicants.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Exam {
    private int id;
    private int idEnrollee;
    private String subject;
    private int score;
}
