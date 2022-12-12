package website.applicants.controllers;


import website.applicants.dao.EnrolleeDBDao;
import website.applicants.models.Enrollee;
import website.applicants.service.EnrolleeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AddController {
   private EnrolleeService enrolleeService = new EnrolleeService(new EnrolleeDBDao());

    @GetMapping("/add")
    public String enrolleeForm(Model model) {
        model.addAttribute("title","Добавление абитуриента");
        Enrollee enrollee = new Enrollee ();
        model.addAttribute("enrollee", enrollee);
        model.addAttribute("number",  enrolleeService.sizeEnrollees());
        return "add";
    }
    @PostMapping("/add")
    public String enrolleeSubmit(@ModelAttribute Enrollee enrollee, Model model) {
        if (!enrollee.getFullName().equals("") && enrollee.getBirthday()!=null) {
            enrollee.setId(enrolleeService.sizeEnrollees());
            enrolleeService.save(enrollee);
        }
        List<Enrollee> enrollees = enrolleeService.getAllEnrolles();
        model.addAttribute("enrollees", enrollees);
        return "/enrollees";
    }

}
