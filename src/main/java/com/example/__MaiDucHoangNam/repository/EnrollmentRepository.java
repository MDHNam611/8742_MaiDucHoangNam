package com.example.__MaiDucHoangNam.repository;

import com.example.__MaiDucHoangNam.entity.Course;
import com.example.__MaiDucHoangNam.entity.Enrollment;
import com.example.__MaiDucHoangNam.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    // Hàm này giúp kiểm tra xem sinh viên đã đăng ký môn này chưa để tránh đăng ký trùng
    boolean existsByStudentAndCourse(Student student, Course course);

    List<Enrollment> findByStudent(Student student);
}