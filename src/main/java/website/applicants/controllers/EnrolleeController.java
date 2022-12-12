package website.applicants.controllers;

import website.applicants.dao.EnrolleeDBDao;
import website.applicants.dao.ExamDBDao;
import website.applicants.models.Exam;
import website.applicants.service.EnrolleeService;
import website.applicants.service.ExamService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class EnrolleeController {
    EnrolleeService enrolleeService = new EnrolleeService(new EnrolleeDBDao());
    ExamService examService = new ExamService(new ExamDBDao());

    private int idEnrollee;

    @GetMapping("/enrollees")
    public String enrollees(Model model){
        model.addAttribute("title","Список абитуриентов");
        model.addAttribute("enrollees", enrolleeService.getAllEnrolles());
        return "enrollees";
    }

    @GetMapping("/enrollee/{id}")
    public String enrollee(@PathVariable final int id, Model model){
        model.addAttribute("title", "Экзамены абитуриента");
        model.addAttribute("enrollee", enrolleeService.getEnrollee(id));
        model.addAttribute("exams", examService.getExamsEnrolleeId(id));
        return "enrollee";
    }

    @GetMapping("/exam{id}")
    public String examForm(@PathVariable final int id, Model model) {
        model.addAttribute("title", "Добавить экзамены");
        idEnrollee = id;
        model.addAttribute("subjects", getSubjects());
        model.addAttribute("exam", new Exam());
        model.addAttribute("id", id);
        return "exam";
    }

    private List<String> getSubjects(){
        ArrayList<String> subjects= new ArrayList<>();
        List<Exam> exams = examService.getAllExams();
        exams.forEach(x->subjects.add(x.getSubject()));
        return subjects.stream().distinct().collect(Collectors.toList());
    }

    @PostMapping("/exam")
    public String examSubmit(Exam exam, Model model) {
        model.addAttribute("title", "Список абитуриентов");
        if (!exam.getSubject().equals("")) {
            exam.setIdEnrollee(idEnrollee);
            examService.save(exam);
        }
        model.addAttribute("enrollees", enrolleeService.getAllEnrolles());
        return "/enrollees";
    }
}
