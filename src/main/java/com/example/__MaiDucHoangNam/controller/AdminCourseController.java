package com.example.__MaiDucHoangNam.controller;

import com.example.__MaiDucHoangNam.entity.Course;
import com.example.__MaiDucHoangNam.repository.CategoryRepository;
import com.example.__MaiDucHoangNam.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils; // Thêm import
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile; // Thêm import

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/admin/courses")
public class AdminCourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CategoryRepository categoryRepository;

    // R - Read: Hiển thị danh sách
    @GetMapping
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "admin/course-list";
    }

    // C - Create: Hiển thị form thêm mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("categories", categoryRepository.findAll());
        return "admin/course-form";
    }

    // U - Update: Hiển thị form sửa
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Course course = courseService.getCourseById(id);
        if (course != null) {
            model.addAttribute("course", course);
            model.addAttribute("categories", categoryRepository.findAll());
            return "admin/course-form";
        }
        return "redirect:/admin/courses";
    }

    // Cập nhật hàm lưu dữ liệu để xử lý Upload File
    @PostMapping("/save")
    public String saveCourse(@ModelAttribute("course") Course course, 
                             @RequestParam("imageFile") MultipartFile multipartFile) {
        
        // Kiểm tra nếu người dùng có chọn file ảnh mới
        if (!multipartFile.isEmpty()) {
            // 1. Lấy tên file và làm sạch đường dẫn
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            
            // 2. Thiết lập đường dẫn ảo để lưu vào Database (Trùng với WebConfig)
            course.setImage("/uploads/" + fileName);

            // 3. Đường dẫn vật lý để lưu file trên ổ cứng
            String uploadDir = "C:/course-uploads/"; 
            Path uploadPath = Paths.get(uploadDir);

            try {
                // Tạo thư mục nếu chưa tồn tại
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                
                // Lưu file vào thư mục
                try (InputStream inputStream = multipartFile.getInputStream()) {
                    Path filePath = uploadPath.resolve(fileName);
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Nếu không chọn ảnh mới (khi Edit), giữ lại ảnh cũ từ Database
            if (course.getId() != null) {
                Course existingCourse = courseService.getCourseById(course.getId());
                course.setImage(existingCourse.getImage());
            }
        }

        courseService.saveCourse(course);
        return "redirect:/admin/courses";
    }

    // D - Delete: Xóa học phần
    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") Long id) {
        courseService.deleteCourse(id);
        return "redirect:/admin/courses";
    }
}