package com.learnsphere.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnsphere.entities.Course;
import com.learnsphere.entities.Lesson;
import com.learnsphere.repositories.LessonRepository;
import com.learnsphere.repositories.TrainerRepository;

@Service
public class TrainerServiceImplementation implements TrainerService{

	@Autowired
	TrainerRepository trepo;
	
	@Autowired
	LessonRepository lrepo;
	@Override
	public String saveCourse(Course c) {
		// TODO Auto-generated method stub
		trepo.save(c);
		return null;
	}


	public List<Course> viewAllCourses() {
		// TODO Auto-generated method stub
		return trepo.findAll();
	}



	public String saveLesson(Integer courseId, Lesson l) {
		// TODO Auto-generated method stub
		Course course =  trepo.findCourseByCourseId(courseId);
		l.setCourse(course);
		if(course != null)
		{
			lrepo.save(l);
			course.addLesson(l);
			trepo.save(course);
			return "lesson Saved";
		}
		
		return "Lesson not saved ";
		
	}

	@Override
	public Course fetchCourse(Integer courseId) {
		// TODO Auto-generated method stub
		return trepo.findCourseByCourseId(courseId);
	}


	@Override
	public List<Course> fetchAllCourses() {
		// TODO Auto-generated method stub
		return null;
	}

}
