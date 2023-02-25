package com.example.demo.repository;
import com.example.demo.entity.Course;
import com.example.demo.entity.Group;
import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
    @Query("select s from Student s join s.group gr where gr.id= :id")
    List<Student> getStudentsByGroupId(@Param("id") Long id);

    @Query("select c from Course c join c.groups gr1 where gr1.id= :id")
    List<Course>getCoursesByGroupId(@Param("id") Long id);

    @Query("select  s from Student s  join s.group gr where gr.id= :groupId and s.name = :studentName")
    List<Student> search(@Param("studentName") String studentName, @Param("groupId") Long groupId);
}
