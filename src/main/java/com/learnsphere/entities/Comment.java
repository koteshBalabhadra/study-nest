package com.learnsphere.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer commentId;
	String comment;
}
