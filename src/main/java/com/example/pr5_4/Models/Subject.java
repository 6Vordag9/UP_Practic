package com.example.pr5_4.Models;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.List;
import java.util.Set;
@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long idSubject;
    @Size(min = 3, message = "The nameSubject cannot be less than 3 characters")
    @NotBlank(message = "NameSubject is required")
    private String nameSubject;
    @ManyToOne
    @JoinColumn(name="course_id", nullable=false)
    private Course courses;




    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    @OneToMany(mappedBy="subjects")
    private List<Grade> grades;

    public Subject() {
    }
    @ManyToMany(mappedBy = "subjectList")
    private List<Class> classList;

    @ManyToMany(mappedBy = "subjectScheduleList")
    private List<Schedule> scheduleList;

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public List<Class> getClassList() {
        return classList;
    }

    public void setClassList(List<Class> classList) {
        this.classList = classList;
    }

    public Course getCourses() {
        return courses;
    }

    public void setCourses(Course courses) {
        this.courses = courses;
    }

    public Subject(Long idSubject, String nameSubject) {
        this.idSubject = idSubject;
        this.nameSubject = nameSubject;
    }

    public Long getidSubject() {
        return idSubject;
    }

    public void setidSubject(Long idSubject) {
        this.idSubject = idSubject;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }
}
