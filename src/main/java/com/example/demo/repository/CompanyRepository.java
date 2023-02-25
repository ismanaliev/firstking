package com.example.demo.repository;


import com.example.demo.entity.Company;
import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
    @Query(" select c from Course c join c.company com where com.id= :id")
    List<Course>getCoursesByCompanyId(@Param("id") Long id);

    @Query("select s from Student s join " +
            " s.group g join g.course c join c.company com where com.id= :id")
    List<Student>getStudentsByCompanyId(@Param("id") Long id);

}
