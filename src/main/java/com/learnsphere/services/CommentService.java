package com.learnsphere.services;

import java.util.List;

import com.learnsphere.entities.Comment;

public interface CommentService {

	List<Comment> fetchAllComments();

	void addComment(Comment comment);
}
