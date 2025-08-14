package com.api.water_sytem_management_java.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb_teachers")
public class Teacher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String email;
    private String phoneNumber;
    private String specialization;

    @Enumerated(EnumType.STRING)
    private TeacherType type;

    public Teacher() {
    }

    public Teacher(String name, String email, String phoneNumber, String specialization, TeacherType type) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
        this.type = type;
    }
}
