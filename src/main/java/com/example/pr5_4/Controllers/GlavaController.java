package com.example.pr5_4.Controllers;

import com.example.pr5_4.Models.*;
import com.example.pr5_4.Models.Class;
import com.example.pr5_4.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@PreAuthorize("hasAnyAuthority('GLAVA')")
@RequestMapping("/Glava")
@Controller
public class GlavaController {

    @Autowired
    public GlavaController(UserRepository userRepository, ClassRepository classRepository,
                          CourseRepository courseRepository,ScheduleRepository scheduleRepository,
                          SpecialityRepository specialityRepository,SubjectRepository subjectRepository,GradeRepository gradeRepository) {
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

    //------------------------------------------------------//

    @GetMapping("/Index")
    public String Index(Model model) {
        return "/Glava/Index";
    }

    @GetMapping("/Course/index")
    public String courseIndex(@ModelAttribute("course") Course course,
                             Model model) {
        model.addAttribute("title", "Курсы");
        model.addAttribute("courses", courseRepository.findAll());
        return "/Glava/Course/index";
    }

    @PostMapping("/Course/index")
    public String createCourse(@ModelAttribute("course") Course course, Model model, BindingResult bindingResult) {
        model.addAttribute("title", "Курсы");
        courseRepository.save(course);
        return "redirect:/Glava/Course/index";
    }

    @GetMapping("/Course/index/{id}")
    public String deleteCourse(@PathVariable("id") Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id));
        courseRepository.delete(course);
        return "redirect:/Glava/Course/index";
    }

    @PostMapping("/Course/updateCourse/{id}")
    public String updatePhone(@PathVariable("id") Long id, @Valid Course course, BindingResult result, Model model) {
        course.setIdCourse(id);
        courseRepository.save(course);
        return "redirect:/Glava/Course/index";
    }

    @GetMapping("/Course/updateCourse/{id}")
    public String editPhone(Model model, @PathVariable("id") Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id));
        model.addAttribute("course", course);
        model.addAttribute("title", "Курсы");
        return "/Glava/Course/updateCourse";
    }



//------------------------------------------------------//

    //------------------------------------------------------//



    @GetMapping("/Class/index")
    public String classIndex(@ModelAttribute("aclass") Class aclass,
                              Model model) {
        model.addAttribute("title", "Классы");
        model.addAttribute("objects", classRepository.findAll());
        return "/Glava/Class/index";
    }

    @PostMapping("/Class/index")
    public String createClass(@ModelAttribute("aclass") Class aclass, Model model) {
        model.addAttribute("title", "Классы");
        classRepository.save(aclass);
        return "redirect:/Glava/Class/index";
    }

    @GetMapping("/Class/index/{id}")
    public String deleteClass(@PathVariable("id") Long id) {
        Class aclass = classRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id));
        classRepository.delete(aclass);
        return "redirect:/Glava/Class/index";
    }

    @PostMapping("/Class/updateClass/{id}")
    public String updateClass(@PathVariable("id") Long id, @Valid Class classe, BindingResult result, Model model) {
        classe.setIdClass(id);
        classRepository.save(classe);
        return "redirect:/Glava/Class/index";
    }

    @GetMapping("/Class/updateClass/{id}")
    public String editClass(Model model, @PathVariable("id") Long id) {
        Class classe = classRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id));
        model.addAttribute("object", classe);
        model.addAttribute("title", "Классы");
        return "/Glava/Class/updateClass";
    }



//------------------------------------------------------//
@GetMapping("/Grade/index")
public String gradeGrade(@ModelAttribute("grade") Grade grade,
                         Model model) {
    model.addAttribute("title", "Оценки");
    model.addAttribute("grades", gradeRepository.findAll());
    model.addAttribute("subjects", subjectRepository.findAll());
    model.addAttribute("users", userRepository.findAll());
    return "/Glava/Grade/index";
}
    @PostMapping("/Grade/index")
    public String createGrade(@ModelAttribute("grade") Grade grade, Model model,
                              @RequestParam Long idUser,@RequestParam Long idSubject, BindingResult bindingResult) {
        model.addAttribute("title", "Оценки");
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", userRepository.findAll());
            model.addAttribute("subjects", subjectRepository.findAll());
            return "/Glava/Grade/index";
        }
        User user = userRepository.findById(idUser).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + idUser));
        Subject subject = subjectRepository.findById(idSubject).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + idSubject));

        grade.setUsers(user);
        grade.setSubjects(subject);
        gradeRepository.save(grade);
        return "redirect:/Glava/Grade/index";
    }
    @GetMapping("/Grade/index/{id}")
    public String deleteGrade(@PathVariable("id") Long id) {
        Grade phone = gradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id));
        gradeRepository.delete(phone);
        return "redirect:/Glava/Grade/index";
    }
    @PostMapping("/Grade/updateGrade/{id}")
    public String updateGrade(@PathVariable("id") Long id, @Valid Grade grade, BindingResult result,
                              @RequestParam Long idUser,@RequestParam Long idSubject, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("users", userRepository.findAll());
            model.addAttribute("subjects", subjectRepository.findAll());
            return "/Glava/Grade/updateGrade";
        }
        User user = userRepository.findById(idUser).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + idUser));
        Subject subject = subjectRepository.findById(idSubject).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + idSubject));

        grade.setUsers(user);
        grade.setSubjects(subject);
        grade.setIdGrade(id);
        gradeRepository.save(grade);
        return "redirect:/Glava/Grade/index";
    }
    @GetMapping("/Grade/updateGrade/{id}")
    public String editGrade(Model model, @PathVariable("id") Long id) {
        Grade grade = gradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id));
        model.addAttribute("grade", grade);
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("subject", subjectRepository.findAll());
        model.addAttribute("title", "Оценки");
        return "/Glava/Grade/updateGrade";
    }

    //------------------------------------------------------//
    @GetMapping("/Schedule/index")
    public String scheduleIndex(@ModelAttribute("schedule") Schedule schedule,
                             Model model) {
        model.addAttribute("title", "Расписание");
        model.addAttribute("schedules", scheduleRepository.findAll());
        model.addAttribute("courses", courseRepository.findAll());

        return "/Glava/Schedule/index";
    }
    @PostMapping("/Schedule/index")
    public String createSchedule(@ModelAttribute("schedule") Schedule schedule, Model model,
                              @RequestParam Long idCourse, BindingResult bindingResult) {
        model.addAttribute("title", "Расписание");
        if (bindingResult.hasErrors()) {
            model.addAttribute("courses", courseRepository.findAll());
            return "/Glava/Schedule/index";
        }
        Course course = courseRepository.findById(idCourse).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + idCourse));
        schedule.setCourse(course);
        schedule.nameSchedule= schedule.nameSchedule+" "+course.getNameCourse();
        scheduleRepository.save(schedule);
        return "redirect:/Glava/Schedule/index";
    }
    @GetMapping("/Schedule/index/{id}")
    public String deleteSchedule(@PathVariable("id") Long id) {
        Schedule phone = scheduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id));
        scheduleRepository.delete(phone);
        return "redirect:/Glava/Schedule/index";
    }
    @PostMapping("/Schedule/updateSchedule/{id}")
    public String updateSchedule(@PathVariable("id") Long id, @Valid Schedule schedule, BindingResult result,
                              @RequestParam Long idCourse, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("courses", courseRepository.findAll());
            return "/Glava/Schedule/updateSchedule";
        }
        Course course = courseRepository.findById(idCourse).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + idCourse));

        schedule.setCourse(course);
        schedule.nameSchedule= schedule.nameSchedule+" "+course.getNameCourse();
        schedule.setIdSchedule(id);
        scheduleRepository.save(schedule);
        return "redirect:/Glava/Schedule/index";
    }
    @GetMapping("/Schedule/updateSchedule/{id}")
    public String editSchedule(Model model, @PathVariable("id") Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id));
        model.addAttribute("schedule", schedule);
        model.addAttribute("course", courseRepository.findAll());
        model.addAttribute("title", "Расписание");
        return "/Glava/Schedule/updateSchedule";
    }
    //------------------------------------------------------//
    @GetMapping("/Speciality/index")
    public String specialityIndex(@ModelAttribute("speciality") Speciality speciality,
                                Model model) {
        model.addAttribute("title", "Специальности");
        model.addAttribute("objects", specialityRepository.findAll());

        return "/Glava/Speciality/index";
    }
    @PostMapping("/Speciality/index")
    public String createSpeciality(@ModelAttribute("speciality") Speciality speciality, Model model) {
        model.addAttribute("title", "Специальность");
        specialityRepository.save(speciality);
        return "redirect:/Glava/Speciality/index";
    }
    @GetMapping("/Speciality/index/{id}")
    public String deleteSpeciality(@PathVariable("id") Long id) {
        Speciality speciality = specialityRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id));
        specialityRepository.delete(speciality);
        return "redirect:/Glava/Speciality/index";
    }
    @PostMapping("/Speciality/updateSpeciality/{id}")
    public String updateSpeciality(@PathVariable("id") Long id, @Valid Speciality speciality, Model model) {

        speciality.setIdSpeciality(id);
        specialityRepository.save(speciality);
        return "redirect:/Glava/Speciality/index";
    }
    @GetMapping("/Speciality/updateSpeciality/{id}")
    public String editSpeciality(Model model, @PathVariable("id") Long id) {
        Speciality speciality = specialityRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id));
        model.addAttribute("speciality", speciality);
        model.addAttribute("title", "Специальности");
        return "/Glava/Speciality/updateSpeciality";
    }

    //------------------------------------------------------//
    @GetMapping("/Subject/index")
    public String subjectIndex(@ModelAttribute("subject") Subject subject,
                                Model model) {
        model.addAttribute("title", "Расписание");
        model.addAttribute("subjects", subjectRepository.findAll());
        model.addAttribute("courses", courseRepository.findAll());

        return "/Glava/Subject/index";
    }
    @PostMapping("/Subject/index")
    public String createSubject(@ModelAttribute("Subject") Subject subject, Model model,
                                 @RequestParam Long idCourse, BindingResult bindingResult) {
        model.addAttribute("title", "Расписание");
        if (bindingResult.hasErrors()) {
            model.addAttribute("courses", courseRepository.findAll());
            return "/Glava/Subject/index";
        }
        Course course = courseRepository.findById(idCourse).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + idCourse));
        subject.setCourses(course);
        subjectRepository.save(subject);
        return "redirect:/Glava/Subject/index";
    }
    @GetMapping("/Subject/index/{id}")
    public String deleteSubject(@PathVariable("id") Long id) {
        Subject phone = subjectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id));
        subjectRepository.delete(phone);
        return "redirect:/Glava/Subject/index";
    }
    @PostMapping("/Subject/updateSubject/{id}")
    public String updateSubject(@PathVariable("id") Long id, @Valid Subject subject, BindingResult result,
                                 @RequestParam Long idCourse, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("courses", courseRepository.findAll());
            return "/Glava/Subject/updateSubject";
        }
        Course course = courseRepository.findById(idCourse).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + idCourse));

        subject.setCourses(course);

        subject.setidSubject(id);
        subjectRepository.save(subject);
        return "redirect:/Glava/Subject/index";
    }
    @GetMapping("/Subject/updateSubject/{id}")
    public String editSubject(Model model, @PathVariable("id") Long id) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id));
        model.addAttribute("subject", subject);
        model.addAttribute("course", courseRepository.findAll());
        model.addAttribute("title", "Предметы");
        return "/Glava/Subject/updateSubject";
    }

    //------------------------------------------------------//

    @GetMapping("/ClassSubject/index")
    public String ClassSubjectIndex(Model model) {
        model.addAttribute("title", "Классы для предметов");
        model.addAttribute("classes", classRepository.findAll());
        model.addAttribute("subjects", subjectRepository.findAll());

        return "/Glava/ClassSubject/index";
    }


    @PostMapping("/ClassSubject/index")
    public String UserDogsAdd(@RequestParam Long idClass, @RequestParam Long idSubject, Model model) {
        model.addAttribute("title", "Классы для предметов");
        Subject subject = subjectRepository.findById(idSubject).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + idSubject));
        Class aclass = classRepository.findById(idClass).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + idClass));
        aclass.getSubjectList().add(subject);
        classRepository.save(aclass);
        return "redirect:/Glava/ClassSubject/index";
    }

    @GetMapping("/ClassSubject/index/{id_Class}/{id_Subject}")
    public String UserDogsDelete(@PathVariable(value = "id_Class") long id_Class,@PathVariable(value = "id_Subject") Long id_Subject, Model model) {
        Subject subject = subjectRepository.findById(id_Subject).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id_Subject));
        Class aClass = classRepository.findById(id_Class).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id_Class));
        aClass.getSubjectList().remove(subject);
        classRepository.save(aClass);
        return "redirect:/Glava/ClassSubject/index";
    }
    //------------------------------------------------------//
    @GetMapping("/scheduleSubject/index")
    public String scheduleSubjectIndex(Model model) {
        model.addAttribute("title", "Расписание предметов");
        model.addAttribute("schedulees", scheduleRepository.findAll());
        model.addAttribute("subjects", subjectRepository.findAll());

        return "/Glava/scheduleSubject/index";
    }


    @PostMapping("/scheduleSubject/index")
    public String scheduleSubjectAdd(@RequestParam Long idSchedule, @RequestParam Long idSubject, Model model) {
        model.addAttribute("title", "Классы для предметов");
        Subject subject = subjectRepository.findById(idSubject).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + idSubject));
        Schedule aschedule = scheduleRepository.findById(idSchedule).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + idSchedule));
        aschedule.getSubjectScheduleList().add(subject);
        scheduleRepository.save(aschedule);
        return "redirect:/Glava/scheduleSubject/index";
    }

    @GetMapping("/scheduleSubject/index/{id_Schedule}/{id_Subject}")
    public String scheduleSubjectDelete(@PathVariable(value = "id_Schedule") long id_Schedule, @PathVariable(value = "id_Subject") Long id_Subject, Model model) {

        Subject subject = subjectRepository.findById(id_Subject).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id_Subject));
        Schedule aschedule = scheduleRepository.findById(id_Schedule).orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id_Schedule));
        aschedule.getSubjectScheduleList().remove(subject);
        scheduleRepository.save(aschedule);
        return "redirect:/Glava/scheduleSubject/index";
    }
}
