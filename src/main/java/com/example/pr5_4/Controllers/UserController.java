package com.example.pr5_4.Controllers;

import com.example.pr5_4.Models.Course;
import com.example.pr5_4.Models.Grade;
import com.example.pr5_4.Models.Schedule;
import com.example.pr5_4.Models.User;
import com.example.pr5_4.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.example.pr5_4.Config.UsernameLogin.usernameLogin;

@PreAuthorize("hasAnyAuthority('STUDENT')")
@RequestMapping("/Student")
@Controller
public class UserController {
    @Autowired
    public UserController(UserRepository userRepository, ClassRepository classRepository,
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
    @GetMapping("/Index")
    public String Index(Model model) {
        return "/Student/Index";
    }
    @GetMapping("/Grade/index")
    public String gradeGrade(@ModelAttribute("grade") Grade grade,
                             Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        if (principal instanceof UserDetails) {
            usernameLogin = ((UserDetails)principal).getUsername();
        } else {
            usernameLogin = principal.toString();
        }
        User user = userRepository.findUserByLogin(usernameLogin);
        model.addAttribute("title", "Оценки");
        model.addAttribute("gradees", gradeRepository.findGradesByUserId(user.getIdUser()));
        model.addAttribute("subjects", subjectRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        Course course =user.getCourses();


        return "/Student/Grade/index";

    }
    @GetMapping("/scheduleSubject/index")
    public String scheduleSubjectIndex(Model model) {
        model.addAttribute("schedules", scheduleRepository.findAll());
        model.addAttribute("title", "Расписание предметов");
        model.addAttribute("subjects", subjectRepository.findAll());

        return "/Student/scheduleSubject/index";
    }
}
