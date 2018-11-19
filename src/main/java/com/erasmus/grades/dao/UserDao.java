package com.erasmus.grades.dao;

import com.erasmus.grades.model.User;

public interface UserDao {

	User findById(int id);
	
	User findByUserName(String username);
	
}

