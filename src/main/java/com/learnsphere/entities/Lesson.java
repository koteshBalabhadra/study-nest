package com.learnsphere.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Lesson {
	@Id
	Integer id;
	String lname;
	String ltopics;
	String llink;
	@ManyToOne
	Course course;
	
}
