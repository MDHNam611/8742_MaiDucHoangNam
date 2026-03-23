package com.example.__MaiDucHoangNam.controller;

import com.example.__MaiDucHoangNam.entity.Course;
import com.example.__MaiDucHoangNam.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private CourseService courseService;

    @GetMapping({"/", "/home"})
    public String home(Model model, 
                       @RequestParam(value = "page", defaultValue = "1") int pageNo,
                       @RequestParam(value = "keyword", required = false) String keyword) {
        int pageSize = 5; 
        
        // Truyền thêm keyword vào hàm service
        Page<Course> page = courseService.getCoursesPaginated(keyword, pageNo, pageSize);

        model.addAttribute("courses", page.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        
        // Giữ lại keyword để đẩy ngược ra view (giúp ô tìm kiếm không bị trắng sau khi tìm)
        model.addAttribute("keyword", keyword); 

        return "home";
    }
}