package com.example.demo.service.impl;


import com.example.demo.entity.*;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.GroupRepository;
import com.example.demo.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, CourseRepository courseRepository) {
        this.groupRepository = groupRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public void addGroup(Group group, Long courseId) {
        Course course = courseRepository.getById(courseId);
        List<Course>courses=new ArrayList<>();
        courses.add(course);
        group.setCourse(courses);
        groupRepository.save(group);
    }

    @Override
    public void updateGroup(Long id, Group group,Long courseId) {
        Course course = courseRepository.getById(courseId);
        List<Course>courses=new ArrayList<>();
        courses.add(course);
        Group group1 = groupRepository.getById(id);
        group1.setGroupName(group.getGroupName());
        group1.setDateOfStart(group.getDateOfStart());
        group1.setDateOfFinish(group.getDateOfFinish());
        group1.setCourse(courses);
        groupRepository.save(group1);
    }

    @Override
    public Group getById(Long id) {
        return groupRepository.findById(id).get();
    }

    @Override
    public void deleteGroup(Group group) {
      groupRepository.delete(group);
    }

    @Override
    public List<Student> getStudentsByGroupId(Long id) {
        return groupRepository.getStudentsByGroupId(id);
    }

    @Override
    public List<Course> getCoursesByGroupId(Long id) {
        return groupRepository.getCoursesByGroupId(id);
    }

    @Override
    public List<Student> search(String studentName,Long groupId) {
        return groupRepository.search(studentName,groupId);
    }

}
