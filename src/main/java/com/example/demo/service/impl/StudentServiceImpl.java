package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void addStudent(Student student,Long groupId) {
        Group group = groupRepository.getById(groupId);
        student.setGroup(group);
        studentRepository.save(student);
    }

    @Override
    public void updateStudent(Long id, Student student,Long groupId) {
        Group group = groupRepository.getById(groupId);
        Student student1 = studentRepository.getById(id);
        student1.setName(student.getName());
        student1.setLastName(student.getLastName());
        student1.setEmail(student.getEmail());
        student1.setStudyFormat(student.getStudyFormat());
        student1.setGroup(group);
        studentRepository.save(student1);
    }

    @Override
    public Student getById(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public void deleteStudent(Student student) {
      studentRepository.delete(student);
    }


}
