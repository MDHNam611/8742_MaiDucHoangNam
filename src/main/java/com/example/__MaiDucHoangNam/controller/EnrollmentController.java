package com.example.__MaiDucHoangNam.controller;

import com.example.__MaiDucHoangNam.entity.Course;
import com.example.__MaiDucHoangNam.entity.Enrollment;
import com.example.__MaiDucHoangNam.entity.Student;
import com.example.__MaiDucHoangNam.repository.EnrollmentRepository;
import com.example.__MaiDucHoangNam.service.CourseService;
import com.example.__MaiDucHoangNam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDate;

@Controller
@RequestMapping("/enroll")
public class EnrollmentController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @PostMapping("/{courseId}")
    public String enrollCourse(@PathVariable("courseId") Long courseId, Principal principal) {
        // Lấy username của người đang đăng nhập
        String username = principal.getName();
        Student student = studentService.findByUsername(username);
        Course course = courseService.getCourseById(courseId);

        if (student != null && course != null) {
            // Kiểm tra xem đã đăng ký chưa
            if (!enrollmentRepository.existsByStudentAndCourse(student, course)) {
                Enrollment enrollment = new Enrollment();
                enrollment.setStudent(student);
                enrollment.setCourse(course);
                enrollment.setEnrollDate(LocalDate.now()); //
                
                enrollmentRepository.save(enrollment);
                return "redirect:/home?enrollSuccess=true"; // Thành công
            } else {
                return "redirect:/home?enrollError=already_enrolled"; // Đã đăng ký rồi
            }
        }
        return "redirect:/home?enrollError=failed";
    }
}