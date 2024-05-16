package com.example.Crud_OnetoOne.repository;

import com.example.Crud_OnetoOne.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Long> {

    Page<Course> findAllByCourseNameContainsIgnoreCase(String courseName, Pageable pageable);
    List<Course> findAllByCourseNameContainsIgnoreCase(String courseName);
}
