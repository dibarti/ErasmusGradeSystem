package com.erasmus.grades.controller;

import com.erasmus.grades.model.User;
import com.erasmus.grades.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    //    @Autowired
    private LoginService authenticateService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("userAttribute", new User());
        System.out.println("index");
        return "login";
    }

    // Checks if the user credentials are valid or not.
    @RequestMapping(value = "/validate", method = RequestMethod.POST)
//    public ModelAndView validateUsr(@RequestParam("username") String username, @RequestParam("password") String password) {
    public ModelAndView validateUsr(@ModelAttribute("userAttribute") User user) {
        String msg = "";
        authenticateService = new LoginService();
        boolean isValid = authenticateService.findUser(user.getUsername(), user.getPassword());
        System.out.println("Is user valid? " + isValid);

        if (isValid) {
            msg = "Welcome " + user.getUsername() + "!";
            return new ModelAndView("index", "user", user);
        } else {
            msg = "Invalid credentials";
            return new ModelAndView("login");
        }
    }

}
