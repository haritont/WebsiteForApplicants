package website.applicants.controllers;


import website.applicants.dao.EnrolleeDBDao;
import website.applicants.models.Enrollee;
import website.applicants.service.EnrolleeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AddController {
    private final EnrolleeService enrolleeService = new EnrolleeService(new EnrolleeDBDao());

    @GetMapping("/add")
    public String enrolleeForm(Model model) {
        model.addAttribute("title", "Добавить абитуриента");
        model.addAttribute("enrollee", new Enrollee());
        model.addAttribute("number", enrolleeService.sizeEnrollees());
        return "add";
    }

    @PostMapping("/add")
    public String enrolleeSubmit(@ModelAttribute Enrollee enrollee, Model model) {
        model.addAttribute("title", "Список абитуриентов");
        if (!enrollee.getFullName().equals("") && enrollee.getBirthday() != null) {
            enrollee.setId(enrolleeService.sizeEnrollees());
            enrolleeService.save(enrollee);
        }
        model.addAttribute("enrollees", enrolleeService.getAllEnrolles());
        return "/enrollees";
    }
}
