package com.example.baitap2.service;

import com.example.baitap2.entity.Department;
import com.example.baitap2.repository.DepartmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository repo;

    public DepartmentService(DepartmentRepository repo) {
        this.repo = repo;
    }

    // Nhận Boolean status (có thể null)
    public List<Department> getAllDepartments(Boolean status) {
        if (status == null) return repo.findAll();
        return repo.findByStatus(status);
    }

    // (tuỳ chọn) overload cũ để ai gọi không truyền tham số vẫn chạy
    public List<Department> getAllDepartments() {
        return getAllDepartments(null);
    }

    public Department getDepartmentById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found: " + id));
    }

    public Department saveDepartment(Department d) { return repo.save(d); }

    public Department updateDepartment(Integer id, Department d) {
        Department db = getDepartmentById(id);
        db.setName(d.getName());
        db.setStatus(d.isStatus());
        return repo.save(db);
    }

    public Department changeStatus(Integer id, boolean status) {
        Department db = getDepartmentById(id);
        db.setStatus(status);
        return repo.save(db);
    }

    public void deleteDepartment(Integer id) {
        Department db = getDepartmentById(id);
        repo.delete(db);
    }
}
