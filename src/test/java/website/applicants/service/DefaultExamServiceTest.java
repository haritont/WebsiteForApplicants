package website.applicants.service;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import website.applicants.exceptions.SaveException;
import website.applicants.models.Exam;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class DefaultExamServiceTest {
    @Autowired
    private DefaultExamService service;

    @Test
    void getAllExams() throws SaveException {
        Exam exam1 = new Exam(1, 1, "Предмет1", 100);
        Exam exam2 = new Exam(2, 1, "Предмет2", 100);

        service.save(exam1);
        service.save(exam2);

        val exams = service.getAllExams();

        assertThat(exam1.getSubject()).isEqualTo(exams.get(0).getSubject());
        assertThat(exam2.getSubject()).isEqualTo(exams.get(1).getSubject());
    }

    @Test
    void getSingleExams() throws SaveException {
        Exam exam1 = new Exam(1, 1, "Предмет1", 100);
        Exam exam2 = new Exam(2, 1, "Предмет2", 100);

        service.save(exam1);
        service.save(exam2);

        val exams = service.getSingleExams();

        assertThat(exam1.getSubject()).isEqualTo(exams.get(0));
        assertThat(exam2.getSubject()).isEqualTo(exams.get(1));
    }

    @Test
    void getExamsEnrolleeId() throws SaveException {
        Exam exam1 = new Exam(1, 1, "Предмет1", 100);
        Exam exam2 = new Exam(2, 1, "Предмет2", 100);

        service.save(exam1);
        service.save(exam2);

        val exams = service.getExamsEnrolleeId(1);

        assertThat(exam1.getSubject()).isEqualTo(exams.get(0).getSubject());
        assertThat(exam2.getSubject()).isEqualTo(exams.get(1).getSubject());
    }
}