package com.learnsphere.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.learnsphere.entities.Users;

import jakarta.servlet.http.HttpSession;

@Controller
public class NavController {

	@GetMapping("/index")
	public String index()
	{
		return "index";
	}
	
	@GetMapping("/login")
	public String login()
	{
		return "login";
	}
	
	@GetMapping("/register")
	public String register()
	{
		return "register";
	}
	
//	@GetMapping("/addCourse")
//	public String addCourse(HttpSession session)
//	{
//		return "addCourse";
//	}

	@GetMapping("/error")
	public String error()
	{
		return "error";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session)
	{
		System.out.println(session.getAttributeNames());
		if(session.getAttribute("loggedInUser") != null)
		{
			Users user = (Users)session.getAttribute("loggedInUser");
			System.out.println(user.getUsername());
		}
		session.invalidate();
		return "login";
	}
}
