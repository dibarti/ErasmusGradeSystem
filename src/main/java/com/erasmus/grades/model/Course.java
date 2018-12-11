package com.erasmus.grades.model;

import java.io.Serializable;

public class Course implements Serializable {

    private int courseId;
    private String courseName;
    private int yearDone;

    public Course(int courseId, String courseName, int yearDone) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.yearDone = yearDone;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getYearDone() {
        return yearDone;
    }
}
