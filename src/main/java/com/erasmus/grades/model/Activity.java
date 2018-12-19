package com.erasmus.grades.model;

import java.io.Serializable;

public class Activity implements Serializable {

    private int activityId;
    private String activityName;
    private String activityType;

    public Activity(int activityId, String activityName, String activityType) {
        this.activityId = activityId;
        this.activityName = activityName;
        this.activityType = activityType;
    }

    public int getActivityId() {
        return activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public String getActivityType() {
        return activityType;
    }

}
