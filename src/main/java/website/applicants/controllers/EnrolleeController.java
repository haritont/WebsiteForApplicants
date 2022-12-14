package website.applicants.controllers;

import lombok.RequiredArgsConstructor;
import website.applicants.models.Exam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import website.applicants.service.EnrolleeService;
import website.applicants.service.ExamService;

@Controller
@RequiredArgsConstructor
public class EnrolleeController {
    private final EnrolleeService enrolleeService;
    private final ExamService examService;

    @GetMapping("/enrollees")
    public String enrollees(Model model) {
        model.addAttribute("title", "Список абитуриентов");
        model.addAttribute("enrollees", enrolleeService.getAllEnrolles());
        return "enrollees";
    }

    @GetMapping("/enrollee/{id}")
    public String enrollee(@PathVariable final int id, Model model) {
        model.addAttribute("title", "Экзамены абитуриента");
        model.addAttribute("enrollee", enrolleeService.getEnrollee(id));
        model.addAttribute("exams", examService.getExamsEnrolleeId(id));
        return "enrollee";
    }

    @GetMapping("/exam{id}")
    public String examForm(@PathVariable final int id, Model model) {
        model.addAttribute("title", "Добавить экзамены");
        model.addAttribute("subjects", examService.getSingleExams());
        model.addAttribute("exam", new Exam());
        model.addAttribute("id", id);
        return "exam";
    }

    @PostMapping("/exam")
    public String examSubmit(Exam exam, Model model) {
        model.addAttribute("title", "Список абитуриентов");
        examService.save(exam);
        model.addAttribute("enrollees", enrolleeService.getAllEnrolles());
        return "/enrollees";
    }
}
