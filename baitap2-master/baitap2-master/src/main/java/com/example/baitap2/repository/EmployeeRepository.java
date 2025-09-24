package com.example.baitap2.repository;

import com.example.baitap2.entity.Department;
import com.example.baitap2.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByStatus(boolean status);
}