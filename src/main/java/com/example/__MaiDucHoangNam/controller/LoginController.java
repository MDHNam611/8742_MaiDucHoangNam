package com.example.__MaiDucHoangNam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // THÊM HÀM NÀY ĐỂ TRẢ VỀ TRANG LỖI 403
    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }
}