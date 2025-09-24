package com.example.baitap2.service;

import com.example.baitap2.entity.Department;
import com.example.baitap2.entity.Employee;
import com.example.baitap2.repository.DepartmentRepository;
import com.example.baitap2.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository,
                           DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found: " + id));
    }

    public Employee saveEmployee(Employee employee) {
        // Nếu gửi lên có department.id, bind lại entity thật:
        if (employee.getDepartment() != null && employee.getDepartment().getId() != null) {
            Department dept = departmentRepository.findById(employee.getDepartment().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found: " + employee.getDepartment().getId()));
            employee.setDepartment(dept);
        }
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Integer id, Employee employee) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found: " + id));
        existing.setFullName(employee.getFullName());
        existing.setAddress(employee.getAddress());
        existing.setPhone(employee.getPhone());
        existing.setStatus(employee.isStatus());

        if (employee.getDepartment() != null && employee.getDepartment().getId() != null) {
            Department dept = departmentRepository.findById(employee.getDepartment().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found: " + employee.getDepartment().getId()));
            existing.setDepartment(dept);
        }
        return employeeRepository.save(existing);
    }

    public Employee changeStatus(Integer id, boolean status) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found: " + id));
        existing.setStatus(status);
        return employeeRepository.save(existing);
    }

    public Employee assignToDepartment(Integer empId, Integer deptId) {
        Employee emp = employeeRepository.findById(empId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found: " + empId));
        Department dept = departmentRepository.findById(deptId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found: " + deptId));
        emp.setDepartment(dept);
        return employeeRepository.save(emp);
    }

    public Employee removeFromDepartment(Integer empId) {
        Employee emp = employeeRepository.findById(empId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found: " + empId));
        emp.setDepartment(null);
        return employeeRepository.save(emp);
    }

    public void deleteEmployee(Integer id) {
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found: " + id));
        employeeRepository.delete(emp);
    }

    public List<Employee> getAllEmployees(Boolean status) {
        if (status == null) return employeeRepository.findAll();
        return employeeRepository.findByStatus(status);
    }
}
