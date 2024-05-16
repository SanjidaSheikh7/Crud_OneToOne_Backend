package com.example.Crud_OnetoOne.controller;

import com.example.Crud_OnetoOne.model.ApiResponse;
import com.example.Crud_OnetoOne.model.CourseModel;
import com.example.Crud_OnetoOne.service.CourseService;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/course/v1/")
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/all")
    public ResponseEntity<List<CourseModel>> getAllCourses() {
        List<CourseModel> courseModels = courseService.getAllCourses();
        return new ResponseEntity<>(courseModels, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<CourseModel>> getAllCourseList(@Nullable @RequestParam("courseName") String courseName) {
        List<CourseModel> courseModels = courseService.getAllCourseList(courseName);
        return new ResponseEntity<>(courseModels, HttpStatus.OK);
    }

//    @GetMapping("/pagination")
//    public ResponseEntity<List<CourseModel>> getCoursePagination(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
//        List<CourseModel> courseModels = courseService.getCoursePagination(page,size);
//        return new ResponseEntity<>(courseModels, HttpStatus.OK);
//    }

    @GetMapping("/pagination")
    public ResponseEntity<ApiResponse> getCoursePagination(@Nullable @RequestParam("courseName") String courseName,
                                                           @RequestParam(defaultValue = "1") int page,
                                                           @RequestParam(defaultValue = "10") int size,
                                                           @RequestParam(defaultValue = "id") String sortCol,
                                                           @RequestParam(defaultValue = "ASC") String sortType) {
        ApiResponse courseModels = courseService.getCoursePagination(courseName,page,size,sortCol,sortType);
        return new ResponseEntity<>(courseModels, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CourseModel> getCourseById(@PathVariable("id") Long id) {
        CourseModel courseModel = courseService.getCourseById(id);
        return new ResponseEntity<>(courseModel, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CourseModel> addCourse(@RequestBody CourseModel courseModel) {
        CourseModel newCourse = courseService.addCourse(courseModel);
        return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CourseModel> updateCourseInfo(@PathVariable("id") Long id, @RequestBody CourseModel courseModel) {
        CourseModel updatedEmployee = courseService.updateCourseInfo(id, courseModel);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable("id") Long id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
