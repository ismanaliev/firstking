package com.example.demo.entity;
import com.example.demo.entity.studyFormat.StudyFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String lastName;
    private String email;
    private StudyFormat studyFormat;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
    @Transient
    Long groupId;

}
