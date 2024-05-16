package com.example.Crud_OnetoOne.service;

import com.example.Crud_OnetoOne.globalException.NotFoundException;
import com.example.Crud_OnetoOne.entity.Course;
import com.example.Crud_OnetoOne.entity.Teacher;
import com.example.Crud_OnetoOne.model.ApiResponse;
import com.example.Crud_OnetoOne.model.CourseModel;
import com.example.Crud_OnetoOne.model.TeacherModel;
import com.example.Crud_OnetoOne.repository.CourseRepository;
import com.example.Crud_OnetoOne.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    @Override
    public TeacherModel addTeacher(TeacherModel teacherModel) {
        Course course=courseRepository.findById(teacherModel.getCourseId()).
                orElseThrow(()->new NotFoundException("Course not found with course id " + teacherModel.getCourseId()));

        Teacher teacher=new Teacher().SetTeacher(teacherModel,course);
        teacher= teacherRepository.save(teacher);
        CourseModel courseModel=new CourseModel().SetCourseModel(teacher.getCourse());
        return new TeacherModel().SetTeacherModel(teacher,courseModel);
    }

    @Override
    public TeacherModel getTeacherById(Long id) {
        Teacher teacher= teacherRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Teacher not found."));
        CourseModel courseModel=new CourseModel().SetCourseModel(teacher.getCourse());
        return new TeacherModel().SetTeacherModel(teacher, courseModel);
    }
//    @Override
//    public List<TeacherModel> getAllTeachers() {
//        List<TeacherModel> teacherModelsList=new ArrayList<>();
//        List<Teacher> teacherList= teacherRepository.findAll();
//
//        if(!teacherList.isEmpty()){
//            teacherModelsList= teacherList.stream()
//                    .map(teacher-> {
//                        return new TeacherModel().SetTeacherModel(teacher, new CourseModel().SetCourseModel(teacher.getCourse()));
//                    }).toList();
//        }
//        return teacherModelsList;
//    }

    @Override
    public List<TeacherModel> getAllTeacherList(String teacherName) {
        List<TeacherModel> teacherModelsList=new ArrayList<>();
        List<Teacher> teacherList;
        if(StringUtils.isNotBlank(teacherName)){
            teacherList=teacherRepository.findAllByNameContainsIgnoreCase(teacherName);
        }else{
            teacherList=teacherRepository.findAll();
        }
        if(!teacherList.isEmpty()){
            teacherModelsList= teacherList.stream()
                    .map(teacher-> {
                        return new TeacherModel().SetTeacherModel(teacher, new CourseModel().SetCourseModel(teacher.getCourse()));
                    }).toList();
        }
        return teacherModelsList;
    }

    @Override
    public ApiResponse getTeacherPagination(String teacherName, int page, int size, String sortCol, String sortType) {
        Pageable pageable;
        if(sortType.equalsIgnoreCase("ASC")){
            pageable = PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC,sortCol));
        }else{
            pageable = PageRequest.of(page-1, size, Sort.by(Sort.Direction.DESC,sortCol));
        }

        Page<Teacher> teacherPage;
        if(StringUtils.isNotBlank(teacherName)){
            teacherPage=teacherRepository.findAllByNameContainsIgnoreCase(teacherName,pageable);
        }else {
            teacherPage=teacherRepository.findAll(pageable);
        }

        List<TeacherModel> teacherModels=new ArrayList<>();
        if(!teacherPage.isEmpty()){
            teacherModels= teacherPage.getContent().stream()
                    .map(teacher -> new TeacherModel().SetTeacherModel(teacher, new CourseModel().SetCourseModel(teacher.getCourse())))
                    .toList();
        }
        ApiResponse apiResponse=new ApiResponse().SetResponse(teacherModels,
                teacherPage.getTotalElements(),teacherPage.getTotalPages(),teacherPage.hasNext(),
                teacherPage.hasPrevious(),page);
        return apiResponse;
    }

    @Override
    public TeacherModel updateTeacherInfo(Long id, TeacherModel teacherModel) {
        Teacher existingTeacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher with this " + id + " does not exist"));
        Course course = courseRepository.findById(teacherModel.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course with id " + teacherModel.getCourseId() + " does not exist"));
        existingTeacher = existingTeacher.updateTeacher(teacherModel, course);
        existingTeacher = teacherRepository.save(existingTeacher);
        CourseModel courseModel = new CourseModel().SetCourseModel(existingTeacher.getCourse());
        return new TeacherModel().SetTeacherModel(existingTeacher, courseModel);
    }


    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

//@Override
//public TeacherModel updateTeacherInfo(Long id, TeacherModel teacherModel) {
//    Teacher existingTeacher = teacherRepository.findById(id)
//            .orElseThrow(() -> new RuntimeException("Teacher with this " + id + " does not exist"));
//    Course course = courseRepository.findById(teacherModel.getCourseId())
//            .orElseThrow(() -> new RuntimeException("Course with id " + teacherModel.getCourseId() + " does not exist"));
////    existingTeacher=existingTeacher.updateTeacher(teacherModel,course);
//    existingTeacher.updateTeacher(teacherModel,course);
//    existingTeacher = teacherRepository.save(existingTeacher);
//    return new TeacherModel().SetTeacherModel(existingTeacher, teacherModel.getCourseModel());
//}


}
