package com.erasmus.grades.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "iduser", unique = true, nullable = false)
    private long iduser;
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "role", nullable = false)
    private int role;

    //    @OneToMany(
//            mappedBy = "user",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private List<UserToCourse> courses = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.user")
    private Set<UserToCourse> userToCourses;

    public long getUserId() {
        return iduser;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserProfileType getRole() {
        return UserProfileType.values()[role - 1];
    }

    public Set<UserToCourse> getUserToCourses() {
        return this.userToCourses;
    }

    public void setUserToCourses(Set<UserToCourse> userToCourses) {
        this.userToCourses = userToCourses;
    }

//    public void addCourse(Course course) {
//        UserToCourse userToCourse = new UserToCourse(this, course);
//        courses.add(userToCourse);
//        course.getUsers().add(userToCourse);
//    }
//
//    public void removeCourse(Course course) {
//        for (Iterator<UserToCourse> iterator = courses.iterator();
//             iterator.hasNext(); ) {
//            UserToCourse userToCourse = iterator.next();
//
//            if (userToCourse.getCourse().equals(this) &&
//                    userToCourse.getCourse().equals(course)) {
//                iterator.remove();
//                userToCourse.getCourse().getUsers().remove(userToCourse);
//                userToCourse.setUser(null);
//                userToCourse.setCourse(null);
//            }
//        }
//    }

    public void setUserId(long iduser) {
        this.iduser = iduser;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(int role) {
        this.role = role;
    }

}
