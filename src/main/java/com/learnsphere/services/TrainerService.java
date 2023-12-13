package com.learnsphere.services;

import java.util.List;

import com.learnsphere.entities.Course;
import com.learnsphere.entities.Lesson;

public interface TrainerService {

	String saveCourse(Course c);
	
	String saveLesson(Integer courseId,Lesson l) ;
	
	Course fetchCourse(Integer courseId);
	
	List<Course> fetchAllCourses();
}
