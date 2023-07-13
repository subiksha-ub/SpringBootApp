package com.example.employeeManagement.employeeRepository;

import com.example.employeeManagement.employeeModel.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
