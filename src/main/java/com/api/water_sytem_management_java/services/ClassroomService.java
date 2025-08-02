
package com.api.water_sytem_management_java.services;

import com.api.water_sytem_management_java.controllers.dtos.ClassroomInput;
import com.api.water_sytem_management_java.controllers.dtos.ClassroomOutput;
import com.api.water_sytem_management_java.models.Classroom;
import com.api.water_sytem_management_java.models.Student;
import com.api.water_sytem_management_java.models.Teacher;
import com.api.water_sytem_management_java.repositories.ClassroomRepository;
import com.api.water_sytem_management_java.repositories.StudentRepository;
import com.api.water_sytem_management_java.repositories.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public ClassroomService(ClassroomRepository classroomRepository,
                            TeacherRepository teacherRepository,
                            StudentRepository studentRepository) {
        this.classroomRepository = classroomRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public Classroom createClassroom(ClassroomInput input) {
        Teacher headTeacher = teacherRepository.findById(input.headTeacherId())
                .orElseThrow(() -> new RuntimeException("Head teacher not found"));

        List<Teacher> assistants = input.assistantTeacherIds()
                .stream()
                .map(id -> teacherRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Assistant teacher not found: " + id)))
                .collect(Collectors.toList());

        List<Student> students = input.studentIds()
                .stream()
                .map(id -> studentRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Student not found: " + id)))
                .collect(Collectors.toList());

        Classroom classroom = new Classroom(
                input.name(),
                input.schedule(),
                headTeacher,
                assistants,
                students
        );

        return classroomRepository.save(classroom);
    }

    public List<ClassroomOutput> getAllClassrooms() {
        return classroomRepository.findAll()
                .stream()
                .map(this::mapToClassroomOutput)
                .collect(Collectors.toList());
    }

    public Optional<ClassroomOutput> getClassroomById(UUID id) {
        return classroomRepository.findById(id).map(this::mapToClassroomOutput);
    }

    @Transactional
    public Optional<ClassroomOutput> updateClassroom(UUID id, ClassroomInput input) {
        return classroomRepository.findById(id).map(existing -> {
            existing.setName(input.name());
            existing.setSchedule(input.schedule());

            Teacher headTeacher = teacherRepository.findById(input.headTeacherId())
                    .orElseThrow(() -> new RuntimeException("Head teacher not found"));

            List<Teacher> assistants = input.assistantTeacherIds()
                    .stream()
                    .map(assistantId -> teacherRepository.findById(assistantId)
                            .orElseThrow(() -> new RuntimeException("Assistant teacher not found: " + assistantId)))
                    .collect(Collectors.toList());

            List<Student> students = input.studentIds()
                    .stream()
                    .map(studentId -> studentRepository.findById(studentId)
                            .orElseThrow(() -> new RuntimeException("Student not found: " + studentId)))
                    .collect(Collectors.toList());

            existing.setHeadTeacher(headTeacher);
            existing.setAssistantTeachers(assistants);
            existing.setStudents(students);

            Classroom updated = classroomRepository.save(existing);
            return mapToClassroomOutput(updated);
        });
    }

    public void deleteClassroom(UUID id) {
        classroomRepository.deleteById(id);
    }

    private ClassroomOutput mapToClassroomOutput(Classroom classroom) {
        List<String> assistantNames = classroom.getAssistantTeachers()
                .stream()
                .map(Teacher::getName)
                .collect(Collectors.toList());

        List<UUID> assistantIds = classroom.getAssistantTeachers()
                .stream()
                .map(Teacher::getId)
                .collect(Collectors.toList());

        List<String> studentNames = classroom.getStudents()
                .stream()
                .map(Student::getNome)
                .collect(Collectors.toList());

        List<UUID> studentIds = classroom.getStudents()
                .stream()
                .map(Student::getId)
                .collect(Collectors.toList());

        return new ClassroomOutput(
                classroom.getId(),
                classroom.getName(),
                classroom.getSchedule(),
                classroom.getHeadTeacher().getName(),
                classroom.getHeadTeacher().getId(),
                assistantNames,
                assistantIds,
                studentNames,
                studentIds
        );
    }
}
