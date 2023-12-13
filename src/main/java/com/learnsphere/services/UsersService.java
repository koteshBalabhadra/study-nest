package com.learnsphere.services;

import java.util.List;

import com.learnsphere.entities.Course;
import com.learnsphere.entities.Users;

public interface UsersService {

	String addUser(Users s);

	Users findUserByEmail(String email);
	
	boolean checkEmail(String email);
}
