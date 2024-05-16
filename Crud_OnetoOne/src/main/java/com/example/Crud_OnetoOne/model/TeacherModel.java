package com.example.Crud_OnetoOne.model;

import com.example.Crud_OnetoOne.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherModel {
    private Long id;
    private String name;
    private String email;
    private String phoneNo;
    private String address;
    private Long courseId;
    private CourseModel courseModel;

    public TeacherModel SetTeacherModel(Teacher teacher, CourseModel courseModel){
        this.setId(teacher.getId());
        this.setName(teacher.getName());
        this.setEmail(teacher.getEmail());
        this.setPhoneNo(teacher.getPhoneNo());
        this.setAddress(teacher.getAddress());
        this.setCourseId(courseModel.getId());
        this.setCourseModel(courseModel);
        return this;
    }
}
