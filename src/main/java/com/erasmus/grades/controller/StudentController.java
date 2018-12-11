package com.erasmus.grades.controller;

import com.erasmus.grades.model.SecurityUser;
import com.erasmus.grades.model.StudentGrade;
import com.erasmus.grades.service.StudentService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentController {

//    private List<StudentGrade> getGrades(String format) {
//        String grade1;
//        String grade2;
//        String grade3;
//        if (format.equals("danish")) {
//            grade1 = "10";
//            grade2 = "12";
//            grade3 = "7";
//        } else {
//            grade1 = "B";
//            grade2 = "A";
//            grade3 = "C";
//        }
//
//        Course testing = new Course("Testing", 10);
//        Course databases = new Course("Databases", 10);
//        Course ai = new Course("Artificial intelligence", 10);
//        Calendar c = Calendar.getInstance();
//        List<StudentGrade> grades = new ArrayList<>();
//        grades.add(new StudentGrade("Testing",grade1));
//        grades.add(new StudentGrade("Databases",  grade2));
//        grades.add(new StudentGrade("Artificial intelligence", grade3));
//
//        return grades;
//    }

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public String index(ModelMap model) {
        String format = "danish";

        showStudentPage(format, model);

        return "student";
    }

    @RequestMapping(value = "/student", method = RequestMethod.GET, params = "format")
    public String indexWithFormat(@RequestParam("format") String format, ModelMap model) {
        showStudentPage(format, model);

        return "student";
    }

    private void showStudentPage(String format, ModelMap model) {
        model.addAttribute("user", getUser().getUsername());

        model.addAttribute("format", format);

        long studentId = getUser().getUserId();
        List<StudentGrade> grades = StudentService.getInstance().fetchGradesByStudentId((int) studentId, format);
        model.addAttribute("grades", grades);
    }

    private SecurityUser getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ((SecurityUser) principal);
    }

}