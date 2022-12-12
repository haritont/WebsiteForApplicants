package website.applicants.controllers;

import website.applicants.dao.EnrolleeDBDao;
import website.applicants.dao.ExamDBDao;
import website.applicants.models.Enrollee;
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
        List<Enrollee> enrollees =enrolleeService.getAllEnrolles();
        model.addAttribute("enrollees", enrollees);
        return "enrollees";
    }

    @GetMapping("/enrollee/{id}")
    public String enrollee(@PathVariable int id, Model model){
        Enrollee enrollee = enrolleeService.getEnrollee(id);
        model.addAttribute("enrollee", enrollee);
        List<Exam> exams = examService.getAllExams();
        exams = exams.stream().filter(x->x.getIdEnrollee()==id).collect(Collectors.toList());
        model.addAttribute("exams", exams);
        return "enrollee";
    }

    @GetMapping("/exam{id}")
    public String examForm(@PathVariable int id, Model model) {
        idEnrollee = id;
        model.addAttribute("title","Добавление экзамена'");
        ArrayList<String> subjects= new ArrayList<>();
        List<Exam> exams = examService.getAllExams();
        exams.stream().forEach(x->subjects.add(x.getSubject()));
        List<String> onlySubjects =  subjects.stream().distinct().collect(Collectors.toList());
        model.addAttribute("subjects", onlySubjects);
        Exam exam = new Exam();
        model.addAttribute("exam", exam);
        model.addAttribute("id", id);
        return "exam";
    }

    @PostMapping("/exam")
    public String examSubmit(Exam exam, Model model) {
        if (!exam.getSubject().equals("")) {
            exam.setIdEnrollee(idEnrollee);
            examService.save(exam);
        }
        List<Enrollee> enrollees = enrolleeService.getAllEnrolles();
        model.addAttribute("enrollees", enrollees);
        return "/enrollees";
    }

}
