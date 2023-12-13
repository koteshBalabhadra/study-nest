package com.learnsphere.controller;

import java.util.Enumeration;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learnsphere.entities.Course;
import com.learnsphere.entities.Users;
import com.learnsphere.services.StudentService;
import com.learnsphere.services.TrainerService;
import com.learnsphere.services.UsersService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class OrderCreation {

	@Autowired
	UsersService uservice;
	
	@Autowired
	TrainerService tservice;
	
	@Autowired
	StudentService sservice;

	@PostMapping("/takeOrder")
	@ResponseBody
	public String takeOrder(@RequestParam int amount,
			@RequestParam String email, @RequestParam int courseId,HttpSession session,HttpServletRequest request) {	
		
		//modified
		Enumeration<String> attributes = request.getSession().getAttributeNames();
		if (attributes.hasMoreElements()) {
		    String attribute = (String) attributes.nextElement();
		    Users user = (Users) request.getSession().getAttribute(attribute);
		    System.out.println(attribute+" : "+user.getUsername());
		    if(user.getRole().equals("trainer"))
		    	return "index";
		}
		
		else {
			return "login";
		}

		mapCourseAndUser(email, courseId);	//modified
		Order order=null;
		try {
			  RazorpayClient razorpay=new RazorpayClient("rzp_test_nEiJnwE6QBM8hH",
					  										"Htg2Nim1nGKV1IxC5lBbXd7L");
			  JSONObject orderRequest = new JSONObject();
			  orderRequest.put("amount", amount*100); // amount in the smallest currency unit
			  orderRequest.put("currency", "INR");
			  orderRequest.put("receipt", "order_rcptid_11");

			  order = razorpay.orders.create(orderRequest);
			} catch (RazorpayException e) {
			  // Handle Exception
			  System.out.println(e.getMessage());
			}
		
		return order.toString();
	}
	
	
	public String mapCourseAndUser(String email,Integer courseId) {

		
		Users user = uservice.findUserByEmail(email);
		Course course = tservice.fetchCourse(courseId);
		
		user.getCourseList().add(course);
		course.getUserList().add(user);
		
		uservice.addUser(user);
		tservice.saveCourse(course);
		
		return "Course and order is mapped...";
	}
	
}
