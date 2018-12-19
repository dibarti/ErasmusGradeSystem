package com.erasmus.grades.model;

public class ActivityStudent {

    private Student student;
    private Integer grade;

    public ActivityStudent(Student student, Integer grade) {
        this.student = student;
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public Integer getGrade() {
        return grade;
    }
}
