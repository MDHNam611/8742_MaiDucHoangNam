package com.example.__MaiDucHoangNam.service;

import com.example.__MaiDucHoangNam.entity.Role;
import com.example.__MaiDucHoangNam.entity.Student;
import com.example.__MaiDucHoangNam.repository.RoleRepository;
import com.example.__MaiDucHoangNam.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RoleRepository roleRepository;

    // Tạo bộ mã hóa mật khẩu trực tiếp ở đây để tiện sử dụng
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Student findByUsername(String username) {
        return studentRepository.findByUsername(username);
    }

    public void saveStudent(Student student) {
        // Mã hóa mật khẩu
        student.setPassword(passwordEncoder.encode(student.getPassword()));

        // Lấy quyền STUDENT từ database
        Role studentRole = roleRepository.findByName("STUDENT");
        
        // Nếu trong database chưa có quyền STUDENT thì tự động tạo mới (phòng hờ)
        if (studentRole == null) {
            studentRole = new Role();
            studentRole.setName("STUDENT");
            roleRepository.save(studentRole);
        }

        // Gán quyền và lưu sinh viên 
        student.getRoles().add(studentRole);
        studentRepository.save(student);
    }
}