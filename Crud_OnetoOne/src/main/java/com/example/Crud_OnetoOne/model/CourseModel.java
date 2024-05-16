package com.example.Crud_OnetoOne.model;

import com.example.Crud_OnetoOne.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseModel {
    Long id;
    String courseName;
    int courseCredit;
    public CourseModel SetCourseModel(Course course){
        this.setId(course.getId());
        this.setCourseName(course.getCourseName());
        this.setCourseCredit(course.getCourseCredit());
        return this;
    }
}
