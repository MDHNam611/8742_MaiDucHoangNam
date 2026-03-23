package com.example.__MaiDucHoangNam.repository;

import com.example.__MaiDucHoangNam.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    
    // Thêm hàm tìm kiếm theo tên có chứa từ khóa (có phân trang)
    Page<Course> findByNameContainingIgnoreCase(String name, Pageable pageable);
}