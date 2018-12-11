package com.erasmus.grades.controller;

import com.erasmus.grades.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

//    @RequestMapping(value = "/teacher", method = RequestMethod.GET)
//    public String adminPage(ModelMap model) {
//        model.addAttribute("user", getPrincipal());
//        return "teacher";
//    }

    @RequestMapping(value = "/db", method = RequestMethod.GET)
    public String dbaPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "dba";
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "accessDenied";
    }

    @RequestMapping(value = {"/", "/login"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String loginPage(ModelMap model) {
        model.addAttribute("userForm", new User());

        return "login";
    }

//    private void fetchGrades() {
//        SessionFactory sessionFactory = HibernateService.buildSessionFactory();
//        Session session = sessionFactory.getCurrentSession();
//        Transaction tx = session.beginTransaction();
//        String hql = "" +
//                "from User u " +
//                "inner join UserToCourse as utc on u.iduser = utc.pk.user.iduser " +
//                "inner join Course as c on c.courseId = utc.pk.course.courseId " +
//                "where u.iduser = ?";
//
//        Criteria c = session.createCriteria(UserToCourse.class, "utc");
//        c.createAlias("utc.user", "user"); // inner join by default
//        c.createAlias("utc.course", "class");
//        c.add(Restrictions.eq("user.iduser", 1L));
//
////        session.createQuery(c)
////        List result = session.createQuery(hql)
////                .setParameter(0, 1L)
////                .list();
//        System.out.println(c.list());
//
////        for (UserToCourse utc : ((User)c.list().get(0)).getUserToCourses()) {
////            System.out.println("result = " + utc.getCourse().getName() + " " + utc.getUser().getUsername() + " " + utc.getGrade());
////        }
//
//    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

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
