package com.example.baitap2.controller;

import com.example.baitap2.entity.Employee;
import com.example.baitap2.service.EmployeeService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getOne(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Integer id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @PatchMapping("/{id}/status")
    public Employee changeStatus(@PathVariable Integer id, @RequestParam boolean status) {
        return employeeService.changeStatus(id, status);
    }

    @PatchMapping("/{empId}/assign/{deptId}")
    public Employee assignToDepartment(@PathVariable Integer empId, @PathVariable Integer deptId) {
        return employeeService.assignToDepartment(empId, deptId);
    }

    @PatchMapping("/{empId}/remove-department")
    public Employee removeFromDepartment(@PathVariable Integer empId) {
        return employeeService.removeFromDepartment(empId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
    public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
        List<Employee> findByStatus(boolean status);
    }
}
