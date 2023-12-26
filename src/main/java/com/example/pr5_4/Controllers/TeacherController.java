package com.example.pr5_4.Controllers;

import com.example.pr5_4.Models.Class;
import com.example.pr5_4.Models.Grade;
import com.example.pr5_4.Models.Subject;
import com.example.pr5_4.Models.User;
import com.example.pr5_4.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import static com.example.pr5_4.Config.UsernameLogin.usernameLogin;
import javax.validation.Valid;

@PreAuthorize("hasAnyAuthority('TEACHER')")
@RequestMapping("/Teacher")
@Controller
public class TeacherController {


    @Autowired
    public TeacherController(UserRepository userRepository, ClassRepository classRepository,
                           CourseRepository courseRepository, ScheduleRepository scheduleRepository,
                           SpecialityRepository specialityRepository, SubjectRepository subjectRepository, GradeRepository gradeRepository) {
        this.userRepository = userRepository;
        this.classRepository = classRepository;
        this.scheduleRepository = scheduleRepository;
        this.courseRepository = courseRepository;
        this.specialityRepository=specialityRepository;
        this.subjectRepository=subjectRepository;
        this.gradeRepository=gradeRepository;

    }

    private final ClassRepository classRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final ScheduleRepository scheduleRepository;
    private final SpecialityRepository specialityRepository;
    private final SubjectRepository subjectRepository;
    private final GradeRepository gradeRepository;

    @GetMapping("/Grade/index")
    public String gradeGrade(@ModelAttribute("grade") Grade grade,
                             Model model) {
        model.addAttribute("title", "Оценки");
        model.addAttribute("grades", gradeRepository.findAll());
        model.addAttribute("subjects", subjectRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "/Teacher/Grade/index";
    }
    @PostMapping("/Grade/index")
    public String createTeacher(@ModelAttribute("grade") Grade grade, Model model,
                              @RequestParam Long idUser,@RequestParam Long idSubject, BindingResult bindingResult) {
        model.addAttribute("title", "Оценки");
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", userRepository.findAll());
            model.addAttribute("subjects", subjectRepository.findAll());
            return "/Teacher/Grade/index";
        }

        Subject subject = subjectRepository.findById(idSubject).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + idSubject));
        User user = userRepository.findById(idUser).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + idUser));
        grade.setUsers(user);
        grade.setSubjects(subject);
        gradeRepository.save(grade);
        return "redirect:/Teacher/Grade/index";
    }
    @GetMapping("/Grade/index/{id}")
    public String deleteGrade(@PathVariable("id") Long id) {
        Grade phone = gradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id));
        gradeRepository.delete(phone);
        return "redirect:/Teacher/Grade/index";
    }
    @PostMapping("/Grade/updateGrade/{id}")
    public String updateGrade(@PathVariable("id") Long id, @Valid Grade grade, BindingResult result,
                              @RequestParam Long idUser, @RequestParam Long idSubject, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("users", userRepository.findAll());
            model.addAttribute("subjects", subjectRepository.findAll());
            return "/Teacher/Grade/updateGrade";
        }
        User user = userRepository.findById(idUser).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + idUser));
        Subject subject = subjectRepository.findById(idSubject).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + idSubject));

        grade.setUsers(user);
        grade.setSubjects(subject);
        grade.setIdGrade(id);
        gradeRepository.save(grade);
        return "redirect:/Teacher/Grade/index";
    }
    @GetMapping("/Grade/updateGrade/{id}")
    public String editGrade(Model model, @PathVariable("id") Long id) {
        Grade grade = gradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id));
        model.addAttribute("grade", grade);
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("subject", subjectRepository.findAll());
        model.addAttribute("title", "Оценки");
        return "/Teacher/Grade/updateGrade";
    }

}
