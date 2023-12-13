package com.learnsphere.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnsphere.entities.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {

	Lesson findLessonById(Integer lessonId);
}
