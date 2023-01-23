package website.applicants.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import website.applicants.exceptions.GetEnrolleeException;
import website.applicants.exceptions.SaveException;
import website.applicants.models.Exam;

import org.springframework.stereotype.Controller;
import website.applicants.service.EnrolleeService;
import website.applicants.service.ExamService;


@Controller
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EnrolleeController {
    EnrolleeService enrolleeService;
    ExamService examService;

    @GetMapping("/enrollees")
    public ModelAndView enrollees() {
        return new ModelAndView("enrollees").addObject("enrollees", enrolleeService.getAllEnrolles());
    }

    @GetMapping("/enrollee/{id}")
    public ModelAndView enrollee(@PathVariable final int id) throws GetEnrolleeException {
        return new ModelAndView("enrollee").addObject("enrollee", enrolleeService.getEnrollee(id))
            .addObject("exams", examService.getExamsEnrolleeId(id));
    }

    @GetMapping("/exam{idEnrollee}")
    public ModelAndView examForm(@PathVariable final int idEnrollee) {
        return new ModelAndView("exam").addObject("subjects", examService.getSingleExams())
            .addObject("exam", new Exam(idEnrollee));
    }

    @PostMapping("/exam")
    public ModelAndView examSubmit(final Exam exam) throws SaveException {
        examService.save(exam);
        return new ModelAndView("/enrollees").addObject("enrollees", enrolleeService.getAllEnrolles());
    }

    @ExceptionHandler(SaveException.class)
    public ModelAndView handlerSaveException(DataIntegrityViolationException exception) {
        return new ModelAndView("exception").addObject("message", exception.getMessage());
    }

    @ExceptionHandler(GetEnrolleeException.class)
    public ModelAndView handlerGetEnrolleeException(IllegalArgumentException exception) {
        return new ModelAndView("exception").addObject("message", exception.getMessage());
    }
}
