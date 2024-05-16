package com.example.Crud_OnetoOne.entity;

import com.example.Crud_OnetoOne.model.CourseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String courseName;
    int courseCredit;

    public Course SetCourse(CourseModel courseModel){
        this.setId(courseModel.getId());
        this.setCourseName(courseModel.getCourseName());
        this.setCourseCredit(courseModel.getCourseCredit());
        return this;
    }
}
