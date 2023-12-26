package com.example.pr5_4.Models;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.List;
import java.util.Set;
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idSchedule;
    @Size(min = 3, message = "The nameSchedule cannot be less than 3 characters")
    @NotBlank(message = "NameSchedule is required")
    public String nameSchedule;

    public List<Subject> getSubjectScheduleList() {
        return subjectScheduleList;
    }

    public void setSubjectScheduleList(List<Subject> subjectScheduleList) {
        this.subjectScheduleList = subjectScheduleList;
    }

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name="ScheduleSubject",
            joinColumns=@JoinColumn(name="shedule_id"),
            inverseJoinColumns=@JoinColumn(name="Subject_id"))
    private List<Subject> subjectScheduleList;




    @ManyToOne
    @JoinColumn(name="course_id", nullable=false)
    private Course course;

    public Schedule(Long idSchedule, String nameSchedule) {
        this.idSchedule = idSchedule;
        this.nameSchedule = nameSchedule;

    }

    public  Schedule(){

    }
    public Long getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(Long idSchedule) {
        this.idSchedule = idSchedule;
    }

    public String getNameSchedule() {
        return nameSchedule;
    }

    public void setNameSchedule(String nameSchedule) {
        this.nameSchedule = nameSchedule;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
