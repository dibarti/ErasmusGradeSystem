package com.erasmus.grades.service;

import com.erasmus.grades.model.User;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateTemplate;

public class LoginService extends HibernateService {

    public LoginService() { }

    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @SuppressWarnings( { "unchecked", "deprecation" } )
    public boolean findUser(String uname, String upwd) {
        System.out.println("Checking the user in the database");
        boolean isValidUser = false;
        String sqlQuery = "FROM user u where u.username=? and u.password=?";
        try {
            buildSessionFactory();
            Session session = sessionFactoryObj.openSession();

            

            User user = session.get(User.class, 1L);
            if (user.getPassword().equals(upwd)) {
                System.out.println("Id= " + user.getUserId() + ", Name= " + user.getUsername() + ", Password= " + user.getPassword());
                isValidUser = true;
            }
        } catch(Exception e) {
            isValidUser = false;
            System.out.println("An error occurred while fetching the user details from the database: " + e);
        }
        return isValidUser;
    }
}
