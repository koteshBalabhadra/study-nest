package com.learnsphere.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnsphere.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment,Integer>{

}
