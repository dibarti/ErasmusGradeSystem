package com.erasmus.grades.service;

import com.erasmus.grades.dao.UserDao;
import com.erasmus.grades.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    public User findById(int id) {
        return dao.findById(id);
    }

    public User findByUsername(String username) {
        return dao.findByUserName(username);
    }

}
