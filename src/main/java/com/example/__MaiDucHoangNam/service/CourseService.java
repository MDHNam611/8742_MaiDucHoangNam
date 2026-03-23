package com.example.__MaiDucHoangNam.service;

import com.example.__MaiDucHoangNam.entity.Course;
import com.example.__MaiDucHoangNam.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    // Phân trang cho trang Home
    public Page<Course> getCoursesPaginated(String keyword, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        
        // Nếu người dùng có nhập từ khóa tìm kiếm
        if (keyword != null && !keyword.trim().isEmpty()) {
            return courseRepository.findByNameContainingIgnoreCase(keyword, pageable);
        }
        
        // Nếu không có từ khóa thì lấy tất cả
        return courseRepository.findAll(pageable);
    }

    // Lấy tất cả danh sách cho Admin
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Lưu học phần (Dùng cho cả Create và Update)
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    // Lấy học phần theo ID để Sửa
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    // Xóa học phần
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}