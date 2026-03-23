package com.example.__MaiDucHoangNam.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "enrollment")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student; // 

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course; // 

    private LocalDate enrollDate; // 
}