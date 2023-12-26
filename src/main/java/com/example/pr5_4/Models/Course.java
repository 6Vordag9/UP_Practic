package com.example.pr5_4.Models;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.List;
import java.util.Set;
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idCourse;
    @Size(min = 3, message = "The nameCourse cannot be less than 3 characters")
    @NotBlank(message = "NameCourse is required")
    private String nameCourse;
    @OneToMany(mappedBy="courses")
    private List<Subject> subjects;
    @OneToMany(mappedBy="courses")
    private List<User> users;

    @OneToMany(mappedBy = "course")
    private List<Schedule> schedule;

    public List<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public Course(){

    }





    public Course(Long idCourse, String nameCourse) {
        this.idCourse = idCourse;
        this.nameCourse = nameCourse;

    }

    public Long getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Long idCourse) {
        this.idCourse = idCourse;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }
}
