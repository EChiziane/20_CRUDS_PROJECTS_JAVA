package com.api.water_sytem_management_java.controllers.dtos;

import com.api.water_sytem_management_java.models.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb_classrooms")
public class Classroom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String schedule; // e.g. "Mon-Wed-Fri 08:00-10:00"

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "head_teacher_id")
    private Teacher headTeacher;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "classroom_assistant_teachers",
            joinColumns = @JoinColumn(name = "classroom_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private List<Teacher> assistantTeachers;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "classroom_students",
            joinColumns = @JoinColumn(name = "classroom_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;

    private final LocalDateTime createdAt = LocalDateTime.now();

    public Classroom() {}

    public Classroom(String name, String schedule, Teacher headTeacher,
                     List<Teacher> assistantTeachers, List<Student> students) {
        this.name = name;
        this.schedule = schedule;
        this.headTeacher = headTeacher;
        this.assistantTeachers = assistantTeachers;
        this.students = students;
    }
}
