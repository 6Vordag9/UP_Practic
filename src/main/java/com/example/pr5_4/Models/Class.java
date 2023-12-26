package com.example.pr5_4.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Class {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idClass;
    @Size(min = 3, message = "The nameClass cannot be less than 3 characters")
    @NotBlank(message = "NameClass is required")
    private String nameClass;
    public Class(){
    }
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name="ClassSubjects",
            joinColumns=@JoinColumn(name="class_id"),
            inverseJoinColumns=@JoinColumn(name="Subject_id"))
    private List<Subject> subjectList;

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public Class(Long idClass, String nameClass) {
        this.idClass = idClass;
        this.nameClass = nameClass;
    }

    public Long getIdClass() {
        return idClass;
    }

    public void setIdClass(Long idClass) {
        this.idClass = idClass;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }
}
