package com.example.pr5_4.Models;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.List;
import java.util.Set;
@Entity
public class Speciality {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idSpeciality;
    @Size(min = 3, message = "The nameSpeciality cannot be less than 3 characters")
    @NotBlank(message = "NameSpeciality is required")
    private String nameSpeciality;
    @OneToMany(mappedBy="speciality")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Speciality(){

    }
    public Speciality(Long idSpeciality, String nameSpeciality) {
        this.idSpeciality = idSpeciality;
        this.nameSpeciality = nameSpeciality;
    }

    public Long getIdSpeciality() {
        return idSpeciality;
    }

    public void setIdSpeciality(Long idSpeciality) {
        this.idSpeciality = idSpeciality;
    }

    public String getNameSpeciality() {
        return nameSpeciality;
    }

    public void setNameSpeciality(String nameSpeciality) {
        this.nameSpeciality = nameSpeciality;
    }
}
