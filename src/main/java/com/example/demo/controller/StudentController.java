package com.example.demo.controller;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("students")
public class StudentController {
    private final StudentService studentService;
    private final GroupService groupService;
    private final StudentRepository studentRepository;
    @Autowired
    public StudentController(StudentService studentService, GroupService groupService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.groupService = groupService;
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public String students(Model model){
        model.addAttribute("students",studentService.getAllStudents());
        return "student/students";
    }
    @GetMapping("/addStudent")
    public String add(Model model){
        model.addAttribute("student",new Student());
        model.addAttribute("groups",groupService.getAllGroups());
        return "student/addStudent";
    }
    @PostMapping("/saveStudent")
    public String save(@ModelAttribute("student") Student student){
        studentService.addStudent(student,student.getGroupId());
        return "redirect:/students";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model){
        List<Group>groups = groupService.getAllGroups();
        model.addAttribute("groups",groups);
        Student student = studentService.getById(id);
        model.addAttribute("student",student);
        return "student/updateStudent";
    }
    @RequestMapping(value = "/{id}", method = {RequestMethod.PATCH, RequestMethod.GET})
    public String updateStudent(@PathVariable("id") Long id,@ModelAttribute("student") Student student){
        studentService.updateStudent(id,student,student.getGroupId());
        return "redirect:/students";
    }
    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE,RequestMethod.GET})
    public String delete(@PathVariable("id") Long id){
     //   Student student = studentService.getById(id);
        studentRepository.deleteById(id);
        return "redirect:/students";
    }
}
