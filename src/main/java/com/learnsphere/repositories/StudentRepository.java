package com.learnsphere.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import com.learnsphere.entities.Lesson;

public interface StudentRepository extends JpaRepository<Lesson, Integer>{

	Lesson findLessonById(Integer id);
}
