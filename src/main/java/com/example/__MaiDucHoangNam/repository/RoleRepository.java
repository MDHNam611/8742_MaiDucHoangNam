package com.example.__MaiDucHoangNam.repository;

import com.example.__MaiDucHoangNam.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name); // Hàm để tìm Role theo tên
}