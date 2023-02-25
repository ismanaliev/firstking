package com.example.demo.service;


import com.example.demo.entity.*;

import java.util.List;

public interface GroupService {
    List<Group> getAllGroups();
    void addGroup(Group group,Long courseId);
    void updateGroup(Long id, Group group, Long courseId);
    Group getById(Long id);
    void deleteGroup(Group group);
    List<Student>getStudentsByGroupId(Long id);
    List<Course>getCoursesByGroupId(Long id);
    List<Student> search(String studentName,Long groupId);
}
