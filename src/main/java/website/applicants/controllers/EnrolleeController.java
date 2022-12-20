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
        examService.save(new Exam(1, 1, "y", 80));
        model.addAttribute("enrollees", enrolleeService.getAllEnrolles());
        return "enrollees";
    }

    @GetMapping("/enrollee/{id}")
    public String enrollee(@PathVariable final int id, Model model) {
        model.addAttribute("enrollee", enrolleeService.getEnrollee(id))
                .addAttribute("exams", examService.getExamsEnrolleeId(id));
        return "enrollee";
    }

    @GetMapping("/exam{idEnrollee}")
    public String examForm(@PathVariable final int idEnrollee, Model model) {
        model.addAttribute("subjects", examService.getSingleExams())
                .addAttribute("exam", new Exam())
                .addAttribute("idEnrollee", idEnrollee);
        return "exam";
    }

    @PostMapping("/exam")
    public String examSubmit(final Exam exam, Model model) {
        examService.save(exam);
        model.addAttribute("enrollees", enrolleeService.getAllEnrolles());
        return "/enrollees";
    }
}
