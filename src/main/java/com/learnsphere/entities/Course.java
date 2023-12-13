package com.learnsphere.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
	
@NoArgsConstructor
@Data
@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer courseId;
	
	String courseName;
	Integer coursePrice;

	@OneToMany
	List<Lesson> lessonList;
	
	@ManyToMany
	List<Users> userList;

	public void addLesson(Lesson l) {
		// TODO Auto-generated method stub
		lessonList.add(l);
	}


}
