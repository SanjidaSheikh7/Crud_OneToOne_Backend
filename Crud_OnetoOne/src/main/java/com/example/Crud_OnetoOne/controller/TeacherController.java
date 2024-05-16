package com.example.Crud_OnetoOne.controller;

import com.example.Crud_OnetoOne.entity.Teacher;
import com.example.Crud_OnetoOne.model.ApiResponse;
import com.example.Crud_OnetoOne.model.TeacherModel;
import com.example.Crud_OnetoOne.service.TeacherService;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/teacher/v1/")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

//    @GetMapping("/all")
//    public ResponseEntity<List<TeacherModel>> getAllTeachers() {
//        List<TeacherModel> teacherModels = teacherService.getAllTeachers();
//        return new ResponseEntity<>(teacherModels, HttpStatus.OK);
//    }

    @GetMapping("/list")
    public ResponseEntity<List<TeacherModel>> getAllTeacherList(@Nullable @RequestParam("teacherName") String teacherName) {
        List<TeacherModel> teacherModels = teacherService.getAllTeacherList(teacherName);
        return new ResponseEntity<>(teacherModels, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<TeacherModel> getTeacherById(@PathVariable("id") Long id) {
        TeacherModel teacherModel = teacherService.getTeacherById(id);
        return new ResponseEntity<>(teacherModel, HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<ApiResponse> getTeacherPagination(@Nullable @RequestParam("teacherName") String teacherName,
                                                           @RequestParam(defaultValue = "1") int page,
                                                           @RequestParam(defaultValue = "10") int size,
                                                           @RequestParam(defaultValue = "id") String sortCol,
                                                           @RequestParam(defaultValue = "ASC") String sortType) {
        ApiResponse courseModels = teacherService.getTeacherPagination(teacherName,page,size,sortCol,sortType);
        return new ResponseEntity<>(courseModels, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<TeacherModel> addTeacher(@RequestBody TeacherModel teacherModel) {
        TeacherModel newTeacher = teacherService.addTeacher(teacherModel);
        return new ResponseEntity<>(newTeacher, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TeacherModel> updateTeacherInfo(@PathVariable("id") Long id, @RequestBody TeacherModel teacherModel) {
        TeacherModel updatedEmployee = teacherService.updateTeacherInfo(id, teacherModel);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable("id") Long id) {
        teacherService.deleteTeacher(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
