package com.example.Crud_OnetoOne.service;

import com.example.Crud_OnetoOne.model.ApiResponse;
import com.example.Crud_OnetoOne.model.CourseModel;

import java.util.List;

public interface CourseService {
    public CourseModel addCourse(CourseModel courseModel);
    public  CourseModel getCourseById(Long id);
    public CourseModel updateCourseInfo(Long id,CourseModel courseModel);
    public List<CourseModel> getAllCourses();
    public List<CourseModel> getAllCourseList(String courseName);
    public void deleteCourse(Long id);


    ApiResponse getCoursePagination(String courseName, int page, int size, String sortCol, String sortType);
}
