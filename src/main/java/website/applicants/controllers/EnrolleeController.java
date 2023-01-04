package website.applicants.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import website.applicants.exceptions.GetEnrolleeException;
import website.applicants.exceptions.SaveException;
import website.applicants.models.Exam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import website.applicants.service.EnrolleeService;
import website.applicants.service.ExamService;


@Controller
@RequiredArgsConstructor
public class EnrolleeController {
    private final EnrolleeService enrolleeService;
    private final ExamService examService;

    @GetMapping("/enrollees")
    public String enrollees(Model model) {
        model.addAttribute("enrollees", enrolleeService.getAllEnrolles());
        return "enrollees";
    }

    @GetMapping("/enrollee/{id}")
    public String enrollee(@PathVariable final int id, Model model) throws GetEnrolleeException {
        model.addAttribute("enrollee", enrolleeService.getEnrollee(id))
            .addAttribute("exams", examService.getExamsEnrolleeId(id));
        return "enrollee";
    }

    @GetMapping("/exam{idEnrollee}")
    public String examForm(@PathVariable final int idEnrollee, Model model) {
        model.addAttribute("subjects", examService.getSingleExams())
            .addAttribute("exam", new Exam(0, idEnrollee, "", 0));
        return "exam";
    }

    @PostMapping("/exam")
    public String examSubmit(final Exam exam, Model model) throws SaveException {
        examService.save(exam);
        model.addAttribute("enrollees", enrolleeService.getAllEnrolles());
        return "/enrollees";
    }

    @ExceptionHandler(SaveException.class)
    public ModelAndView handlerSaveException(Exception exception) {
        ModelAndView model = new ModelAndView("exception");
        model.addObject("message", exception.getMessage());
        return model;
    }

    @ExceptionHandler(GetEnrolleeException.class)
    public ModelAndView handlerGetEnrolleeException(Exception exception) {
        ModelAndView model = new ModelAndView("exception");
        model.addObject("message", exception.getMessage());
        return model;
    }
}
