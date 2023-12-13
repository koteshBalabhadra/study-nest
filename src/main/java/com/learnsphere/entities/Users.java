package com.learnsphere.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 Integer id;
	 String username;
	String email;
	 String password;
	 String role;
	@ManyToMany
	 List<Course> courseList;
	
}
