package com.example.demo.service.impl;

import com.example.demo.entity.*;

import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, CompanyRepository companyRepository) {
        this.courseRepository = courseRepository;
        this.companyRepository = companyRepository;
    }
    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public void addCourse(Course course, Long companyId) {
        Company company = companyRepository.getById(companyId);
        course.setCompany(company);
        courseRepository.save(course);
    }

    @Override
    public void updateCourse(Long id, Course course, Long companyId) {
        Company company = companyRepository.getById(companyId);
        Course course1 = courseRepository.getById(id);
        course1.setCourseName(course.getCourseName());
        course1.setDurationMonth(course.getDurationMonth());
        course1.setCompany(company);
       courseRepository.save(course1);
    }


    @Override
    public Course getById(Long id) {
        return courseRepository.findById(id).get();
    }

    @Override
    public void deleteCourse(Course course) {
       courseRepository.delete(course);
    }

    @Override
    public List<Teacher> getTeachersByCourseId(Long id) {
        return courseRepository.getTeachersByCourseId(id);
    }

    @Override
    public List<Group> getGroupsByCourseId(Long id) {
        return courseRepository.getGroupsByCourseId(id);
    }

}
