package com.example.pr5_4.Models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idGrade;
    @Min(2)
    @Max(5)
    private int numberGrade;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User users;

    public Subject getSubjects() {
        return subjects;
    }

    public void setSubjects(Subject subjects) {
        this.subjects = subjects;
    }

    @ManyToOne
    @JoinColumn(name="subject_id", nullable=false)
    private Subject subjects;
    public Long getIdGrade() {
        return idGrade;
    }
    public Grade(){

    }
    public Grade(Long idGrade, int numberGrade) {
        this.idGrade = idGrade;
        this.numberGrade = numberGrade;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public void setIdGrade(Long idGrade) {
        this.idGrade = idGrade;
    }

    public int getNumberGrade() {
        return numberGrade;
    }

    public void setNumberGrade(int numberGrade) {
        this.numberGrade = numberGrade;
    }
}
