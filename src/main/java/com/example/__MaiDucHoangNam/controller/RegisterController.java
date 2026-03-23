package com.example.__MaiDucHoangNam.controller;

import com.example.__MaiDucHoangNam.entity.Student;
import com.example.__MaiDucHoangNam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("student", new Student());
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        // Sau khi đăng ký thành công, chuyển hướng về trang login
        return "redirect:/login?registered";
    }
}