package com.example.pr5_4.Models;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long IdUser;
    @Size(min = 5, max = 20, message = "Логин не может быть меньше 8 и больше 20")
    @NotBlank(message = "Login is required")
    private String Username;
    @Size(min = 8, message = "Пароль не может быть меньше 8 и больше 25")
    @Pattern(regexp = "^.*(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!#$%&? \"]).*$" , message = "Пароль должен содержать заглавные и прописные буквы, цифры и специальные символы")
    @NotBlank(message = "Password is required")
    private String Password;

    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "Role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    @OneToMany(mappedBy="users")
    private List<Grade> grades;

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    @ManyToOne
    @JoinColumn(name="speciality_id", nullable=true)
    private Speciality speciality;

    public Course getCourses() {
        return courses;
    }

    public void setCourses(Course courses) {
        this.courses = courses;
    }

    @ManyToOne
    @JoinColumn(name="course_id", nullable=true)
    private Course courses;

//    @ManyToMany(cascade = { CascadeType.ALL })
//    @JoinTable(
//            name = "Favourites",
//            joinColumns = { @JoinColumn(name = "user_id") },
//            inverseJoinColumns = { @JoinColumn(name = "article_id") }
//    )
//    private List<Speciality> specialitiesList;
//    private List<Course> coursesList;

    public User() {
    }

    public User(Long idUser, String username, String password, Set<Role> roles) {
        IdUser = idUser;
        Username = username;
        Password = password;
        this.roles = roles;
    }

    public Long getIdUser() {
        return IdUser;
    }

    public void setIdUser(Long idUser) {
        IdUser = idUser;
    }

    public String getLogin() {
        return Username;
    }

    public void setLogin(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }




}
