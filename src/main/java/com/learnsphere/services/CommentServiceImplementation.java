package com.learnsphere.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnsphere.entities.Comment;
import com.learnsphere.repositories.CommentRepository;

@Service
public class CommentServiceImplementation implements CommentService{

	
	@Autowired
	CommentRepository crepo;
	
	
	@Override
	public List<Comment> fetchAllComments() {
		return crepo.findAll();
	}


	@Override
	public void addComment(Comment comment) {
				
		crepo.save(comment);
	}

}
