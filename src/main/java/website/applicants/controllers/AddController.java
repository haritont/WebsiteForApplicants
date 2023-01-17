package website.applicants.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import website.applicants.exceptions.SaveException;
import website.applicants.models.Enrollee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import website.applicants.service.EnrolleeService;


@Controller
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AddController {
    EnrolleeService enrolleeService;

    @GetMapping("/add")
    public ModelAndView enrolleeForm() {
        return new ModelAndView("add").addObject("enrollee", new Enrollee());
    }

    @PostMapping("/add")
    public ModelAndView enrolleeSubmit(final Enrollee enrollee) throws SaveException {
        enrolleeService.save(enrollee);
        return new ModelAndView("/enrollees").addObject("enrollees", enrolleeService.getAllEnrolles());
    }

    @ExceptionHandler(SaveException.class)
    public ModelAndView handlerSaveException(Exception exception) {
        return new ModelAndView("exception").addObject("message", exception.getMessage());
    }
}
