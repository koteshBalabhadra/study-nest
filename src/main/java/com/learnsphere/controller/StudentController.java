package com.learnsphere.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learnsphere.entities.Comment;
import com.learnsphere.entities.Course;
import com.learnsphere.entities.Lesson;
import com.learnsphere.entities.Users;
import com.learnsphere.services.CommentService;
import com.learnsphere.services.StudentService;
import com.learnsphere.services.UsersService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	StudentService sservice;
	
	@Autowired
	UsersService uservice;
	
	@Autowired
	CommentService cservice;

	@GetMapping("/purchaseCourse")
	public String purchaseCourse(Model m,HttpServletRequest request,HttpSession session)
	{
		Enumeration<String> attributes = request.getSession().getAttributeNames();
		if (attributes.hasMoreElements()) {
		    String attribute = (String) attributes.nextElement();
		    Users user = (Users) request.getSession().getAttribute(attribute);
		    System.out.println(attribute+" : "+user.getUsername()+user.getRole());
		    if(!user.getRole().equals("student"))
		    	return "index";
		}
		else {
			return "login";
		}
		
		List<Course> list = sservice.viewAllCourses();
		
		Users loggedUsers = (Users) session.getAttribute("loggedInUser");
		
		String email = loggedUsers.getEmail();
		
		Users user = uservice.findUserByEmail(email);
		
		
		List<Course> userCourseLIst = user.getCourseList();
		List<Integer> temp = userCourseLIst.stream().map(obj -> obj.getCourseId()).collect(Collectors.toList());
		
		Set<Integer> setOfCourses = new HashSet<>(temp);
		
		List<Course> resList = new ArrayList<>(); 
		
		for(Course course : list) {
			if(!setOfCourses.contains(course.getCourseId()))
				resList.add(course);
		}
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m.addAttribute("courseList",resList);
		m.addAttribute("session",session);
		m.addAttribute("user",user);
		return "purchaseCourse";
	}
	
	@GetMapping("/myCourses")
	public String myCourses(Model model,HttpSession session ,HttpServletRequest request)
	{
		
		Enumeration<String> attributes = request.getSession().getAttributeNames();

		if (attributes.hasMoreElements()) {
		    String attribute = (String) attributes.nextElement();
		    Users user = (Users) request.getSession().getAttribute(attribute);
		    System.out.println(attribute+" : "+user.getUsername());
		    if(!user.getRole().equals("student"))
		    	return "index";
		}
		else {
			return "login";
		}
		Users loggedUsers = (Users) session.getAttribute("loggedInUser");
		
		String email = loggedUsers.getEmail();
		
		Users user = uservice.findUserByEmail(email);
		
		List<Course> courseList = user.getCourseList();
		
		model.addAttribute("courseList",courseList);
		model.addAttribute("session",session);
		model.addAttribute("user",user);
		return "myCourses";
		
	}
	

	
	@GetMapping("/viewLesson")
	public String viewLesson(@RequestParam("lessonId") int lessonId, Model m, HttpSession session,HttpServletRequest request)
	{
		Enumeration<String> attributes = request.getSession().getAttributeNames();
		if (attributes.hasMoreElements()) {
		    String attribute = (String) attributes.nextElement();
		    Users user = (Users) request.getSession().getAttribute(attribute);
		    System.out.println(attribute+" : "+user.getUsername());
		    m.addAttribute("user",user);
		    if(user.getRole().equals("trainer"))
		    	return "index";
		}
		
		else {
			return "login";
		}

		Lesson lesson = sservice.getLesson(lessonId);
		
		String youtubeUrl = lesson.getLlink();
		
		String videoId = youtubeUrl.substring(youtubeUrl.indexOf("=")+1);
		lesson.setLlink(videoId);
		
		m.addAttribute("lesson",lesson); 
		List<Comment> commlist = cservice.fetchAllComments();
		m.addAttribute("commList",commlist);

		return "myLesson";
		
	}
	
	
	@GetMapping("/login")
	public String login(Model m,HttpServletRequest request)
	{
		Enumeration<String> attributes = request.getSession().getAttributeNames();
		if (attributes.hasMoreElements()) {
		    String attribute = (String) attributes.nextElement();
		    Users user = (Users) request.getSession().getAttribute(attribute);
		    System.out.println("In std  "+attribute+" : "+user.getUsername());
		    m.addAttribute("user",user);
		    if(user.getRole().equals("student"))
		    {
		    	m.addAttribute("user",user);
				return "studentHome";
		    }
		}
		return "login";
	}

}
