package com.erasmus.grades.controller;

import com.erasmus.grades.db.DBTeacherDAO;
import com.erasmus.grades.model.*;
import com.erasmus.grades.service.TeacherService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class TeacherController {

    @RequestMapping(value = "/teacher", method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.addAttribute("user", getUser().getUsername());

        // Fetch courses by teacher
        List<Course> courses = TeacherService.getInstance().fetchCoursesByTeacherId((int) getUser().getUserId());
        model.addAttribute("courses", courses);

        return "teacher";
    }

    @RequestMapping(value = "/teacher/course/{id}", method = RequestMethod.GET)
    public String course(@PathVariable("id") int courseId, ModelMap model) {
        model.addAttribute("user", getUser().getUsername());

        model.addAttribute("courseId", courseId);
        // Fetch activities by teacher
        List<Activity> activities = TeacherService.getInstance().fetchActivitiesByCourseId(courseId);
        model.addAttribute("activities", activities);

        return "course";
    }

    @RequestMapping(value = "/teacher/course/{id}/activity/{activityId}", method = RequestMethod.GET)
    public String activity(@PathVariable("id") int courseId, @PathVariable("activityId") int activityId, ModelMap model) {
        showActivity(courseId, activityId, model);

        return "activity";
    }

    @RequestMapping(value = "/teacher/course/{id}/activity/{activityId}", method = RequestMethod.POST)
    public String activityGrade(@RequestBody UpdateGradeRequest request, @PathVariable("id") int courseId, @PathVariable("activityId") int activityId, ModelMap model) {

        TeacherService.getInstance().updateGrade(request);

        showActivity(courseId, activityId, model);

        return "activity";
    }

    private void showActivity(@PathVariable("id") int courseId, @PathVariable("activityId") int activityId, ModelMap model) {
        model.addAttribute("user", getUser().getUsername());

        model.addAttribute("courseId", courseId);
        model.addAttribute("activityId", activityId);
        // Fetch activities by teacher
        List<ActivityStudent> students = TeacherService.getInstance().fetchStudentsByActivityId(activityId);
        model.addAttribute("students", students);
    }

    private SecurityUser getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ((SecurityUser) principal);
    }

}