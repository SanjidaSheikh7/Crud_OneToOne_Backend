package com.example.Crud_OnetoOne.service;

import com.example.Crud_OnetoOne.globalException.NotFoundException;
import com.example.Crud_OnetoOne.entity.Course;
import com.example.Crud_OnetoOne.model.ApiResponse;
import com.example.Crud_OnetoOne.model.CourseModel;
import com.example.Crud_OnetoOne.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService{
    @Autowired
    private final CourseRepository courseRepository;
    @Override
    public CourseModel addCourse(CourseModel courseModel) {
        Course course=new Course().SetCourse(courseModel);
        course=courseRepository.save(course);
        return new CourseModel().SetCourseModel(course);
    }

    @Override
    public CourseModel getCourseById(Long id) {
        Course course=courseRepository.findById(id).orElseThrow(()->new NotFoundException("Course not found"));
        return new CourseModel().SetCourseModel(course);
    }

    @Override
    public List<CourseModel> getAllCourses() {
        List<Course> courseList=courseRepository.findAll();
        List<CourseModel> courseModels=new ArrayList<>();
        if(!courseList.isEmpty()){
         courseModels= courseList.stream().map(course-> new CourseModel().SetCourseModel(course)).toList();
        }
        return courseModels;
    }

//    @Override
//    public List<CourseModel> getCoursePagination(int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        List<Course> coursePage=courseRepository.findAll(pageable).getContent();
//        List<CourseModel> courseModelPage=new ArrayList<>();
//        if(!coursePage.isEmpty()){
//            courseModelPage= coursePage.stream().map(course-> new CourseModel().SetCourseModel(course)).toList();
//        }
//        return courseModelPage;
//    }

    @Override
    public ApiResponse getCoursePagination(String courseName, int page, int size, String sortCol, String sortType) {
        Pageable pageable;
        if(sortType.equalsIgnoreCase("ASC")){
            pageable = PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC,sortCol));
        }else{
            pageable = PageRequest.of(page-1, size, Sort.by(Sort.Direction.DESC,sortCol));
        }

        Page<Course> coursePage;
        if(StringUtils.isNotBlank(courseName)){
            coursePage=courseRepository.findAllByCourseNameContainsIgnoreCase(courseName,pageable);
        }else {
            coursePage=courseRepository.findAll(pageable);
        }

        List<CourseModel> courseModels=new ArrayList<>();
        if(!coursePage.isEmpty()){
            courseModels= coursePage.getContent().stream()
                .map(course -> new CourseModel().SetCourseModel(course))
                .toList();
        }
        ApiResponse apiResponse=new ApiResponse().SetResponse(courseModels,
                coursePage.getTotalElements(),coursePage.getTotalPages(),coursePage.hasNext(),
                coursePage.hasPrevious(),page);
        return apiResponse;
    }

    @Override
    public List<CourseModel> getAllCourseList(String courseName) {
        List<Course> courseList=new ArrayList<>();
        if(StringUtils.isNotBlank(courseName)){
            courseList=courseRepository.findAllByCourseNameContainsIgnoreCase(courseName);
        }else {
            courseList=courseRepository.findAll();
        }

        List<CourseModel> courseModels=new ArrayList<>();
        if(!courseList.isEmpty()){
            courseModels= courseList.stream().map(course-> new CourseModel().SetCourseModel(course)).toList();
        }
        return courseModels;
    }
    @Override
    public CourseModel updateCourseInfo(Long id, CourseModel courseModel) {
        Course course=courseRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Course not found"));
//        course=new Course().SetCourse(courseModel);
//        return new CourseModel().SetCourseModel(course);
        course.setCourseName(courseModel.getCourseName());
        course.setCourseCredit(courseModel.getCourseCredit());
        Course updatedCourse = courseRepository.save(course);
        return new CourseModel().SetCourseModel(updatedCourse);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }




}
