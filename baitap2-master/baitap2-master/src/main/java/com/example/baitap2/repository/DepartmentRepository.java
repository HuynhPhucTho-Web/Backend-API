package com.example.baitap2.repository;

import com.example.baitap2.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    List<Department> findByStatus(boolean status);
}
