package com.erasmus.grades.model;

public class UpdateGradeRequest {
    private int studentId;
    private int grade;
    private int activityId;

    public UpdateGradeRequest() {
    }

    public UpdateGradeRequest(int studentId, int grade, int activityId) {
        this.studentId = studentId;
        this.grade = grade;
        this.activityId = activityId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getGrade() {
        return grade;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }
}