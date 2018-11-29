package com.erasmus.grades.model;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StudentGrade implements Serializable {
//    private Course course;
    private String course;
    private Date date;
    private String grade;

//    public StudentGrade(Course course, Date date, String grade) {
//        this.course = course;
//        this.date = date;
//        this.grade = grade;
//    }
//
//    public Course getCourse() {
//        return course;
//    }


    public StudentGrade(String course, String grade) {
        this.course = course;
        this.date = new Date();
        this.grade = grade;
    }

    public String getCourse() {
        return course;
    }

    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

    public String getGrade() {
        return grade;
    }
}