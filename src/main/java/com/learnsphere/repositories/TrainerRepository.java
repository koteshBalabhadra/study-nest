package com.learnsphere.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnsphere.entities.Course;

public interface TrainerRepository extends JpaRepository<Course, Integer>{

	Course findCourseByCourseId(Integer courseId);

}
