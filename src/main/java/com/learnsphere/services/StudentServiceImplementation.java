package com.learnsphere.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnsphere.entities.Course;
import com.learnsphere.entities.Lesson;
import com.learnsphere.repositories.StudentRepository;
import com.learnsphere.repositories.TrainerRepository;

@Service
public class StudentServiceImplementation implements StudentService{

	@Autowired
	TrainerRepository trepo;
	
	@Autowired
	StudentRepository srepo;

	@Override
	public List<Course> viewAllCourses() {
		
		return trepo.findAll();
	}


	public Lesson getLesson(int lessonId) {
		// TODO Auto-generated method stub
		return srepo.findLessonById(lessonId);
		
	}
	
	
}
