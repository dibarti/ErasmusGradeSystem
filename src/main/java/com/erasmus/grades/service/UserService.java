package com.erasmus.grades.service;

import com.erasmus.grades.model.User;

public interface UserService {

	User findById(int id);
	
	User findByUsername(String username);
	
}