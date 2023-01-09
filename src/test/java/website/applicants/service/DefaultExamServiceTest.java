package website.applicants.service;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import website.applicants.exceptions.SaveException;
import website.applicants.models.Exam;


@SpringBootTest
class DefaultExamServiceTest {

    private final Exam exam1 = new Exam(1, 1, "Предмет1", 100);
    private final Exam exam2 = new Exam(2, 1, "Предмет2", 100);

    @Autowired
    private DefaultExamService service;

    @Test
    void getAllExams() throws SaveException {
        service.save(exam1);
        service.save(exam2);

        val exams = service.getAllExams();

        Assertions.assertEquals(exam1.getSubject(), exams.get(0).getSubject());
        Assertions.assertEquals(exam2.getSubject(), exams.get(1).getSubject());
    }

    @Test
    void getSingleExams() throws SaveException {
        service.save(exam1);
        service.save(exam2);

        val exams = service.getSingleExams();

        Assertions.assertEquals(exam1.getSubject(), exams.get(0));
        Assertions.assertEquals(exam2.getSubject(), exams.get(1));
    }

    @Test
    void getExamsEnrolleeId() throws SaveException {
        service.save(exam1);
        service.save(exam2);

        val exams = service.getExamsEnrolleeId(1);

        Assertions.assertEquals(exam1.getSubject(), exams.get(0).getSubject());
        Assertions.assertEquals(exam2.getSubject(), exams.get(1).getSubject());
    }
}