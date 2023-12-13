package com.learnsphere.controller;

import java.util.Enumeration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learnsphere.entities.Course;
import com.learnsphere.entities.Lesson;
import com.learnsphere.entities.Users;
import com.learnsphere.services.TrainerServiceImplementation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/trainer")
public class TrainerController {

	
	@Autowired
	TrainerServiceImplementation tservice;
	
	@GetMapping("/addCourse")
	public String addCourse( Model m,HttpServletRequest request)
	{
		Enumeration<String> attributes = request.getSession().getAttributeNames();
		if (attributes.hasMoreElements()) {
		    String attribute = (String) attributes.nextElement();
		    Users user = (Users) request.getSession().getAttribute(attribute);
		    m.addAttribute("user",user);
		    System.out.println(attribute+" : "+user.getUsername());
		    if(!user.getRole().equals("trainer"))
		    	return "login";
		}
		else {
			return "login";
		}
			return "addCourse";
	}

	
	@PostMapping("/saveCourse")
	public String saveCourse(@ModelAttribute Course course,Model m,HttpServletRequest request)
	{
		Enumeration<String> attributes = request.getSession().getAttributeNames();
		if (attributes.hasMoreElements()) {
		    String attribute = (String) attributes.nextElement();
		    Users user = (Users) request.getSession().getAttribute(attribute);
		    m.addAttribute("user",user);
		    System.out.println(attribute+" : "+user.getUsername());
		    if(!user.getRole().equals("trainer"))
		    	return "login";
		}
		else {
			return "login";
		}
		
		if(course.getCourseName().equals(""))
			return "addCourse";
		tservice.saveCourse(course);
		
		List<Course> list = tservice.viewAllCourses();
		m.addAttribute("clist",list);
		
		return "newLesson";
	}
	
	@GetMapping("/viewCourses")
	public String viewCourses(Model m,HttpServletRequest request)
	{
		Enumeration<String> attributes = request.getSession().getAttributeNames();
		if (attributes.hasMoreElements()) {
		    String attribute = (String) attributes.nextElement();
		    Users user = (Users) request.getSession().getAttribute(attribute);
		    m.addAttribute("user",user);
		    System.out.println(attribute+" : "+user.getUsername());
		    if(!user.getRole().equals("trainer"))
		    	return "login";
		}
		else {
			return "login";
		}
		List<Course> list = tservice.viewAllCourses();
//		System.out.println(list);
		m.addAttribute("clist",list);

		return "viewCourses";
	}
	
	@GetMapping("/newLesson")
	public String newLesson(Model m,HttpServletRequest request)
	{
		Enumeration<String> attributes = request.getSession().getAttributeNames();
		if (attributes.hasMoreElements()) {
		    String attribute = (String) attributes.nextElement();
		    Users user = (Users) request.getSession().getAttribute(attribute);
		    m.addAttribute("user",user);
		    System.out.println(attribute+" : "+user.getUsername());
		    if(!user.getRole().equals("trainer"))
		    	return "login";
		}
		else {
			return "login";
		}
		List<Course> list = tservice.viewAllCourses();
		m.addAttribute("clist",list);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "newLesson";
	}
	
	@PostMapping("/addLesson")
	public String addLesson(@RequestParam("courseId") Integer courseId,@ModelAttribute Lesson l,Model m,HttpServletRequest request)
	{
		
		Enumeration<String> attributes = request.getSession().getAttributeNames();
		if (attributes.hasMoreElements()) {
		    String attribute = (String) attributes.nextElement();
		    Users user = (Users) request.getSession().getAttribute(attribute);
		    m.addAttribute("user",user);
		    System.out.println(attribute+" : "+user.getUsername());
		    if(!user.getRole().equals("trainer"))
		    	return "login";
		}
		else {
			return "login";
		}
		System.out.println(tservice.saveLesson(courseId,l));
		
		List<Course> list = tservice.viewAllCourses();
		m.addAttribute("clist",list);
		
		return  "newLesson";
	}
	
	@GetMapping("/login")
	public String login(Model m,HttpServletRequest request)
	{
		Enumeration<String> attributes = request.getSession().getAttributeNames();
		if (attributes.hasMoreElements()) {
		    String attribute = (String) attributes.nextElement();
		    Users user = (Users) request.getSession().getAttribute(attribute);
		    m.addAttribute("user",user);
		    System.out.println(attribute+" : "+user.getUsername());
		    if(user.getRole().equals("trainer"))
		    {
		    	m.addAttribute("user",user);
		    	return "trainerHome";
		    }
		}
		return "login";
	}
	
	
}
