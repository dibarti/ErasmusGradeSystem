package com.erasmus.grades.controller;

import com.erasmus.grades.model.Course;
import com.erasmus.grades.model.StudentGrade;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class StudentController {

    private List<StudentGrade> getGrades(String format) {
        String grade1;
        String grade2;
        String grade3;
        if (format.equals("danish")) {
            grade1 = "10";
            grade2 = "12";
            grade3 = "7";
        } else {
            grade1 = "B";
            grade2 = "A";
            grade3 = "C";
        }

        Course testing = new Course("Testing", 10);
        Course databases = new Course("Databases", 10);
        Course ai = new Course("Artificial intelligence", 10);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        List<StudentGrade> grades = new ArrayList<>();
        grades.add(new StudentGrade(testing, new Date(), grade1));
        grades.add(new StudentGrade(databases, c.getTime(), grade2));
        c.add(Calendar.DATE, -12);
        grades.add(new StudentGrade(ai, c.getTime(), grade3));

        return grades;
    }

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.addAttribute("user", getPrincipal());

        String format = "danish";
        List<StudentGrade> grades = getGrades(format);
        model.addAttribute("grades", grades);
        model.addAttribute("format", format);

        return "student";
    }

    @RequestMapping(value = "/student", method = RequestMethod.GET, params = "format")
    public String indexWithFormat(@RequestParam("format") String format, ModelMap model) {
        model.addAttribute("user", getPrincipal());

        List<StudentGrade> grades = getGrades(format);
        model.addAttribute("grades", grades);
        model.addAttribute("format", format);

        return "student";
    }

//    private void fetchGrades() {
//        SessionFactory sessionFactory = HibernateService.buildSessionFactory();
//        Session session = sessionFactory.getCurrentSession();
//        Transaction tx = session.beginTransaction();
//        String hql = "from UserToCourse utc where utc.pk.user.id = ?";
//        List result = session.createQuery(hql)
//                .setParameter(0, 1L)
//                .list();
//        System.out.println("result = " + result);
//    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

}