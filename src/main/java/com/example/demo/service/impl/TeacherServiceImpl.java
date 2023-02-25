package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }


    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public void addTeacher(Teacher teacher, Long courseId) {
        Course course = courseRepository.getById(courseId);
        teacher.setCourse(course);
        teacherRepository.save(teacher);
    }

    @Override
    public void updateTeacher(Long id, Teacher teacher, Long courseId) {
        Course course = courseRepository.getById(courseId);
        Teacher teacher1 = teacherRepository.getById(id);
        teacher1.setName(teacher.getName());
        teacher1.setLastName(teacher.getLastName());
        teacher1.setEmail(teacher.getEmail());
        teacher1.setCourse(course);
        teacherRepository.save(teacher1);
    }

    @Override
    public Teacher getById(Long id) {
        return teacherRepository.findById(id).get();
    }

    @Override
    public void deleteTeacher(Teacher teacher) {
        teacherRepository.delete(teacher);
    }

    @Override
    public List<Course> getCoursesByTeacherId(Long id) {
        return teacherRepository.getCoursesByTeacherId(id);
    }

    @Override
    public List<Student> sizeOfStudents(Long id) {
        return teacherRepository.sizeOfStudents(id);
    }

}

