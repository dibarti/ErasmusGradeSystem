package com.erasmus.grades.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "class")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "class_id", unique = true, nullable = false)
    private long courseId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "ects", nullable = false)
    private int ects;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.course")
    private Set<UserToCourse> userToCourses = new HashSet<>(0);

    public Course() {
    }

    public Course(String name, int ects) {
        this.name = name;
        this.ects = ects;
    }

    public Course(long courseId, String name, int ects, Set<UserToCourse> userToCourses) {
        this.courseId = courseId;
        this.name = name;
        this.ects = ects;
        this.userToCourses = userToCourses;
    }


    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long classId) {
        this.courseId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEcts() {
        return ects;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }

    public Set<UserToCourse> getUserToCourses() {
        return this.userToCourses;
    }

    public void setUserToCourses(Set<UserToCourse> userToCourses) {
        this.userToCourses = userToCourses;
    }

//    public List<UserToCourse> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<UserToCourse> users) {
//        this.users = users;
//    }
}
