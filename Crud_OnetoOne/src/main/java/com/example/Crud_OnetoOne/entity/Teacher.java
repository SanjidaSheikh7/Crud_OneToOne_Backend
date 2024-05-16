package com.example.Crud_OnetoOne.entity;

import com.example.Crud_OnetoOne.model.TeacherModel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String phoneNo;
    private String address;
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name="courseId",referencedColumnName = "id")
    private Course course;

    public Teacher SetTeacher(TeacherModel teacherModel, Course course){
        this.setId(teacherModel.getId());
        this.setName(teacherModel.getName());
        this.setEmail(teacherModel.getEmail());
        this.setPhoneNo(teacherModel.getPhoneNo());
        this.setAddress(teacherModel.getAddress());
        this.setCourse(course);  //setting course Entity
        return this;
    }
    public Teacher updateTeacher(TeacherModel teacherModel, Course course){
        this.setName(teacherModel.getName());
        this.setEmail(teacherModel.getEmail());
        this.setPhoneNo(teacherModel.getPhoneNo());
        this.setAddress(teacherModel.getAddress());
        this.setCourse(course);  //setting course Entity
        return this;
    }
}
