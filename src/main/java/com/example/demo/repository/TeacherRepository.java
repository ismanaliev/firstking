package com.example.demo.repository;

import com.example.demo.entity.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    @Query("select c from Course c join c.teachers t where t.id= :id")
    List<Course> getCoursesByTeacherId(@Param("id") Long id);
    @Query("select s from Student s join " +
            " s.group g join g.course c join c.teachers t where t.id= :id")
    List<Student> sizeOfStudents(@Param("id") Long id);
}
