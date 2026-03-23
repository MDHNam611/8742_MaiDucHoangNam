package com.example.__MaiDucHoangNam.controller;

import com.example.__MaiDucHoangNam.entity.Enrollment;
import com.example.__MaiDucHoangNam.entity.Student;
import com.example.__MaiDucHoangNam.repository.EnrollmentRepository;
import com.example.__MaiDucHoangNam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class MyCourseController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @GetMapping("/my-courses")
    public String viewMyCourses(Model model, Principal principal) {
        // Lấy thông tin sinh viên đang đăng nhập
        String username = principal.getName();
        Student student = studentService.findByUsername(username);

        if (student != null) {
            // Lấy danh sách các môn học sinh viên này đã đăng ký
            List<Enrollment> enrollments = enrollmentRepository.findByStudent(student);
            model.addAttribute("enrollments", enrollments);
        }

        return "my-courses";
    }
}