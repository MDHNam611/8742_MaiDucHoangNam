package com.example.__MaiDucHoangNam.config;

import com.example.__MaiDucHoangNam.entity.Role;
import com.example.__MaiDucHoangNam.entity.Student;
import com.example.__MaiDucHoangNam.repository.RoleRepository;
import com.example.__MaiDucHoangNam.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Kiểm tra và tạo Role ADMIN
        Role adminRole = roleRepository.findByName("ADMIN");
        if (adminRole == null) {
            adminRole = new Role();
            adminRole.setName("ADMIN");
            roleRepository.save(adminRole);
        }

        // Kiểm tra và tạo tài khoản Admin mặc định
        if (studentRepository.findByUsername("admin") == null) {
            Student admin = new Student();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("123456")); // Mật khẩu là 123456
            admin.setEmail("admin@example.com");
            admin.getRoles().add(adminRole);
            studentRepository.save(admin);
        }
    }
}