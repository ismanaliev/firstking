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
@RequestMapping("courses")
public class CoursesController {
    private final CourseService courseService;
    private final CompanyService companyService;
    private final CourseRepository courseRepository;
    @Autowired
    public CoursesController(CourseService courseService, CompanyService companyService, CourseRepository courseRepository) {
        this.courseService = courseService;
        this.companyService = companyService;
        this.courseRepository = courseRepository;
    }

    @GetMapping
    public String courses(Model model){
        model.addAttribute("courses",courseService.getAllCourse());
        return "course/courses";
    }

    @GetMapping("/addCourse")
    public String add(Model model){
        model.addAttribute("course",new Course());
        model.addAttribute("companies",companyService.getAllCompany());
        return "course/addCourse";
    }
    @PostMapping("/saveCourse")
    public String save(@ModelAttribute("course") Course course){
        courseService.addCourse(course,course.getCompanyId());
        return "redirect:/courses";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model){
        List<Company>companies = companyService.getAllCompany();
        Course course = courseService.getById(id);
        model.addAttribute("course",course);
        model.addAttribute("companies",companies);
        return "course/updateCourse";
    }
    @RequestMapping(value = "/{id}", method= {RequestMethod.PATCH, RequestMethod.GET})
    public String updateCourse9(@PathVariable("id") Long id,@ModelAttribute("course") Course course){
        courseService.updateCourse(id,course, course.getCompanyId());
        //courseRepository.saveAndFlush(course);
        return "redirect:/courses";
    }
    @RequestMapping (value = "/delete/{id}", method= {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable("id") Long id){
     //   Course course = courseService.getById(id);
        courseRepository.deleteById(id);
        return "redirect:/courses";
    }
    @GetMapping("/groups/{id}")
    public String groups(@PathVariable("id")Long id, Model model){
        List<Group> groups = courseService.getGroupsByCourseId(id);
        model.addAttribute("groups",groups);
        return "course/groups";
    }
    @GetMapping("/teacher/{id}")
    public String teachers(@PathVariable("id")Long id, Model model){
        List<Teacher> teachers = courseService.getTeachersByCourseId(id);
        model.addAttribute("teachers",teachers);
        return "course/teachers";
    }

}
