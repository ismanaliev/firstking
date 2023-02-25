package com.example.demo.service;


import com.example.demo.entity.*;

import java.util.List;

public interface TeacherService {
    List<Teacher>getAllTeachers();
    void addTeacher(Teacher teacher,Long courseId);
    void updateTeacher(Long id, Teacher teacher,Long courseId);
    Teacher getById(Long id);
    void deleteTeacher(Teacher teacher);
    List<Course>getCoursesByTeacherId(Long id);
    List<Student> sizeOfStudents(Long id);
}
