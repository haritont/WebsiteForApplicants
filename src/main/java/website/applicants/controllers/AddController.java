package website.applicants.controllers;

import lombok.RequiredArgsConstructor;
import website.applicants.entity.EnrolleeEntity;
import website.applicants.models.Enrollee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import website.applicants.service.EnrolleeService;


@Controller
@RequiredArgsConstructor
public class AddController {
    private final EnrolleeService enrolleeService;

    @GetMapping("/add")
    public String enrolleeForm(Model model) {
        model.addAttribute("title", "Добавить абитуриента");
        EnrolleeEntity enrolleeEntity = new EnrolleeEntity();
        model.addAttribute("enrollee", new Enrollee(enrolleeEntity));
        return "add";
    }

    @PostMapping("/add")
    public String enrolleeSubmit(@ModelAttribute Enrollee enrollee, Model model) {
        model.addAttribute("title", "Список абитуриентов");
        enrolleeService.save(enrollee);
        model.addAttribute("enrollees", enrolleeService.getAllEnrolles());
        return "/enrollees";
    }
}
