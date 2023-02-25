package com.example.demo.service;

import com.example.demo.entity.*;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompany();
    void addCompany(Company company);
    void updateCompany(Long id, Company company);
    Company getById(Long id);
    void deleteCompany(Company company);
    List<Course>getCoursesByCompanyId(Long id);
    List<Student>getStudentsByCompanyId(Long id);
}
