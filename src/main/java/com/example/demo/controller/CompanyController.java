package com.example.demo.controller;



import com.example.demo.entity.Company;
import com.example.demo.entity.*;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("companies")
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyRepository companyRepository;
    @Autowired
    public CompanyController(CompanyService companyService, CompanyRepository companyRepository) {
        this.companyService = companyService;
        this.companyRepository = companyRepository;
    }

    @GetMapping
    public String companies(Model model){
        model.addAttribute("companies",companyService.getAllCompany());
        return "company/companies";
    }
    @GetMapping("/addCompany")
    public String add(Model model){
        model.addAttribute("company",new Company());
        return "company/addCompany";
    }

    @PostMapping("/saveCompany")
    public String save(@ModelAttribute("company") Company company){
        companyService.addCompany(company);
        return "redirect:/companies";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id")Long id, Model model){
        Company company = companyService.getById(id);
        model.addAttribute("company",company);
        return "company/updateCompany";
    }

    @RequestMapping(value = "/{id}", method= {RequestMethod.PATCH, RequestMethod.GET})
    public String updateCompany(@PathVariable("id") Long id, @ModelAttribute("company")Company company){
      //  companyService.updateCompany(id,company);
        companyRepository.saveAndFlush(company);
        return "redirect:/companies";
    }
    @RequestMapping (value = "/delete/{id}", method= {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable("id") Long id){
     //   Company company = companyService.getById(id);
       companyRepository.deleteById(id);
        return "redirect:/companies";
    }
    @GetMapping("/courses/{id}")
    public String courses(@PathVariable("id")Long id, Model model){
        List<Course> courses = companyService.getCoursesByCompanyId(id);
        model.addAttribute("courses",courses);
        return "company/courses";
    }
    @GetMapping("/students/{id}")
    public String students(@PathVariable("id") Long id, Model model){
        List<Student>students = companyService.getStudentsByCompanyId(id);
        model.addAttribute("students",students);
        return "company/students";
    }

}
