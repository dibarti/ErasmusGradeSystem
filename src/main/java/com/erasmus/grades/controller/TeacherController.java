package com.erasmus.grades.controller;

import com.erasmus.grades.model.Course;
import com.erasmus.grades.model.SecurityUser;
import com.erasmus.grades.model.StudentGrade;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

        return "courses";
    }

    @RequestMapping(value = "/teacher/course/{id}", method = RequestMethod.GET)
    public String indexWithFormat(@PathVariable("id") String courseId, ModelMap model) {
        model.addAttribute("user", getUser().getUsername());

        // TODO: Fetch students by course id -> Returns studentname, grade

        return "course";
    }

    private SecurityUser getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ((SecurityUser) principal);
    }

}