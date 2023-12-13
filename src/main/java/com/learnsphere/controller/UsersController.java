package com.learnsphere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnsphere.entities.Users;
import com.learnsphere.services.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UsersController {

	@Autowired
	UsersService service;
	
	@PostMapping("/addUser")
	public String addUser(@ModelAttribute Users user) {
//		 System.out.println(user);
		if(!service.checkEmail(user.getEmail()))
		{
			user.setEmail(user.getEmail().toLowerCase());
			service.addUser(user);
		}
//		 service.addUser(user);
		else {
			System.out.println("Email is present.. try again..");
			return "/addUser";
		}
		return "index";
	}

	@PostMapping("/validateUser")
	public String validateUser(@RequestParam("email") String email,
								@RequestParam("password") String pass,Model m, HttpSession session)
	{
		Users user = service.findUserByEmail(email.toLowerCase());
//		System.out.println(user);
		if(user != null)
		{
//			System.out.println(user.getPassword()+" "+pass);
			if(user.getPassword().equals(pass))
			{
				session.setAttribute("loggedInUser", user);
				m.addAttribute("session",session);
				if(user.getRole().equals("trainer"))
				{
					m.addAttribute("user",user);
					return "trainerHome";
				}
				else {
					m.addAttribute("user",user);
					return "studentHome";
				}
			}
			return "login";
		}
		return "login";
	}
	
	@GetMapping("/retrieve")
	@ResponseBody
	public Users retrieve(HttpSession session, Model model) {
	    String name = (String) session.getAttribute("name");
	    Users user= (Users) session.getAttribute("name");
	    System.err.println(user);
	    model.addAttribute("name", name);
	    return user;
	}
//	@GetMapping("/addCourse")
//	public String addCourse( HttpSession session)
//	{
//		Users user = (Users) session.getAttribute("loggedInUser");
//		if(user != null && user.getRole()=="trainer")
//		return "addCourse";
//		return "login";
//	}
}
