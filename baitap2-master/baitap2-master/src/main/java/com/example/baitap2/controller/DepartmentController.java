package com.example.baitap2.controller;

import com.example.baitap2.entity.Department;
import com.example.baitap2.service.DepartmentService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<Department> getAll() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Department getOne(@PathVariable Integer id) {
        return departmentService.getDepartmentById(id);
    }

    @PostMapping
    public Department create(@RequestBody Department department) {
        return departmentService.saveDepartment(department);
    }

    @PutMapping("/{id}")
    public Department update(@PathVariable Integer id, @RequestBody Department department) {
        return departmentService.updateDepartment(id, department);
    }

    @PatchMapping("/{id}/status")
    public Department changeStatus(@PathVariable Integer id, @RequestParam boolean status) {
        return departmentService.changeStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build(); // 204
    }

    public interface DepartmentRepository extends JpaRepository<Department, Integer> {
        List<Department> findByStatus(boolean status);
    }

}
