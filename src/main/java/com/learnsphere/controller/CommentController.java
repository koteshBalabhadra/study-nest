package com.learnsphere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learnsphere.entities.Comment;
import com.learnsphere.services.CommentService;

@RestController
public class CommentController {

	@Autowired
	CommentService cservice;
	
	 @PostMapping("/addcomm")
	    public String addcomm(
	            @RequestParam("id") Integer id,
	            @RequestParam("comment") String comment
	    ) {
	        // Do something with the received parameters
	        Comment com = new Comment();
	        com.setComment(comment);
	        System.out.println(com);
	        cservice.addComment(com);
	        return "data Received successfully";
	    }
}
