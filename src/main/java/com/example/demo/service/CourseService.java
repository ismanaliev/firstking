package com.example.demo.service;

import com.example.demo.entity.*;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourse();
    void addCourse(Course course, Long companyId);
    void updateCourse(Long id, Course course,Long companyId);
    Course getById(Long id);
    void deleteCourse(Course course);
    List<Teacher>getTeachersByCourseId(Long id);
    List<Group>getGroupsByCourseId(Long id);

}
