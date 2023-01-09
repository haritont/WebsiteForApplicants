package website.applicants.service;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import website.applicants.exceptions.GetEnrolleeException;
import website.applicants.exceptions.SaveException;
import website.applicants.models.Enrollee;

import java.sql.Date;


@SpringBootTest
class DefaultEnrolleeServiceTest {
    private final Enrollee enrollee1 = new Enrollee(1, "Имя1", new Date(12));
    private final Enrollee enrollee2 = new Enrollee(2, "Имя2", new Date(12));

    @Autowired
    private DefaultEnrolleeService service;

    @Test
    void getAllEnrolles() throws SaveException {
        service.save(enrollee1);
        service.save(enrollee2);

        val enrollees = service.getAllEnrolles();

        Assertions.assertEquals(enrollee1.getFullName(), enrollees.get(0).getFullName());
        Assertions.assertEquals(enrollee2.getFullName(), enrollees.get(1).getFullName());
    }

    @Test
    void getEnrollee() throws GetEnrolleeException, SaveException {
        service.save(enrollee1);
        service.save(enrollee2);

        Assertions.assertEquals(enrollee1.getFullName(), service.getEnrollee(1).getFullName());
        Assertions.assertEquals(enrollee2.getFullName(), service.getEnrollee(2).getFullName());
    }
}