package com.example.Crud_OnetoOne.repository;

import com.example.Crud_OnetoOne.entity.Course;
import com.example.Crud_OnetoOne.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    List<Teacher> findAllByNameContainsIgnoreCase(String teacherName);
    Page<Teacher> findAllByNameContainsIgnoreCase(String teacherName, Pageable pageable);
}
