package com.erasmus.grades.model;


import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StudentGrade implements Serializable {
    private String activityName;
    private String activityType;
    private String grade;
    private int weight;
    private Date date;

    public StudentGrade(String activityName, String activityType, String grade, int weight, Date date) {
        this.activityName = activityName;
        this.activityType = activityType;
        this.grade = grade;
        this.weight = weight;
        this.date = date;
    }

    public String getActivityName() {
        return activityName;
    }

    public String getActivityType() {
        return StringUtils.capitalize(activityType);
    }

    public String getGrade() {
        return grade;
    }

    public int getWeight() {
        return weight;
    }

    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }
}