package com.learnsphere.services;

import java.util.List;
import java.util.Optional;

import com.learnsphere.entities.Course;
import com.learnsphere.entities.Lesson;

public interface StudentService {

	List<Course> viewAllCourses();
	
	Lesson getLesson(int lessonId);
}
