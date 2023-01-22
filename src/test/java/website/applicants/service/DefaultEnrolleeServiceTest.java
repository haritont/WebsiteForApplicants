package website.applicants.service;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import website.applicants.exceptions.GetEnrolleeException;
import website.applicants.exceptions.SaveException;
import website.applicants.models.Enrollee;

import java.sql.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class DefaultEnrolleeServiceTest {
    @Autowired
    private DefaultEnrolleeService service;

    @Test
    void getAllEnrolles() throws SaveException {
        Enrollee enrollee1 = new Enrollee(1, "Имя1", new Date(12));
        Enrollee enrollee2 = new Enrollee(2, "Имя2", new Date(12));

        service.save(enrollee1);
        service.save(enrollee2);

        val enrollees = service.getAllEnrolles();

        assertThat(enrollee1.getFullName()).isEqualTo(enrollees.get(0).getFullName());
        assertThat(enrollee2.getFullName()).isEqualTo(enrollees.get(1).getFullName());
    }

    @Test
    void getEnrollee() throws GetEnrolleeException, SaveException {
        Enrollee enrollee1 = new Enrollee(1, "Имя1", new Date(12));
        Enrollee enrollee2 = new Enrollee(2, "Имя2", new Date(12));

        service.save(enrollee1);
        service.save(enrollee2);

        assertThat(enrollee1.getFullName()).isEqualTo(service.getEnrollee(1).getFullName());
        assertThat(enrollee2.getFullName()).isEqualTo(service.getEnrollee(2).getFullName());
    }
}