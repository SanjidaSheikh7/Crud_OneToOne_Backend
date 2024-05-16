package com.example.Crud_OnetoOne.service;

import com.example.Crud_OnetoOne.model.ApiResponse;
import com.example.Crud_OnetoOne.model.TeacherModel;

import java.util.List;

public interface TeacherService {
    public TeacherModel addTeacher(TeacherModel teacherModel);
    public TeacherModel getTeacherById(Long id);
    public TeacherModel updateTeacherInfo(Long id,TeacherModel teacherModel);
//    public  List<TeacherModel> getAllTeachers();
    public void deleteTeacher(Long id);
    public List<TeacherModel> getAllTeacherList(String teacherName);

    ApiResponse getTeacherPagination(String teacherName, int page, int size, String sortCol, String sortType);
}
