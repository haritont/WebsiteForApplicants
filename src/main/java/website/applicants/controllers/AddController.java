package website.applicants.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import website.applicants.exceptions.SaveException;
import website.applicants.models.Enrollee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import website.applicants.service.EnrolleeService;


@Controller
@RequiredArgsConstructor
public class AddController {
    private final EnrolleeService enrolleeService;

    @GetMapping("/add")
    public String enrolleeForm(Model model) {
        model.addAttribute("enrollee", new Enrollee());
        return "add";
    }

    @PostMapping("/add")
    public String enrolleeSubmit(final Enrollee enrollee, Model model) throws SaveException {
        enrolleeService.save(enrollee);
        model.addAttribute("enrollees", enrolleeService.getAllEnrolles());
        return "/enrollees";
    }

    @ExceptionHandler(SaveException.class)
    public ModelAndView handlerSaveException(Exception exception) {
        ModelAndView model = new ModelAndView("exception");
        model.addObject("message", exception.getMessage());
        return model;
    }
}
