package com.learnsphere.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnsphere.entities.Course;
import com.learnsphere.entities.Users;
import com.learnsphere.repositories.UsersRepository;



@Service
public class UsersServiceImplementation implements UsersService{

	@Autowired
	UsersRepository repo;
	public UsersServiceImplementation() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String addUser(Users u) {
		// TODO Auto-generated method stub
		repo.save(u);
		return u.getUsername()+" saved successfully";
	}

	@Override
	public Users findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return repo.findByEmail(email);
	}

	@Override
	public boolean checkEmail(String email) {
		// TODO Auto-generated method stub
		return repo.existsByEmail(email);
	}


}
