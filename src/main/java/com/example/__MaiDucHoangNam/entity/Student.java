package com.example.__MaiDucHoangNam.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId; // [cite: 6]

    private String username; // [cite: 6]
    private String password; // [cite: 6]
    private String email; // [cite: 6]

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "student_role", // 
        joinColumns = @JoinColumn(name = "student_id"), // 
        inverseJoinColumns = @JoinColumn(name = "role_id") // 
    )
    private Set<Role> roles = new HashSet<>();
}