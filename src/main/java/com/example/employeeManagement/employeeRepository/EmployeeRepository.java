package com.example.employeeManagement.employeeRepository;

import com.example.employeeManagement.employeeModel.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//ilike
    @Query(value = "SELECT * from employee WHERE lower(firstname) like %:search% or lower(lastname) like %:search%;",nativeQuery = true)
    public List<Employee> searchByName(@Param("search") String search);

    @Query(value = "SELECT * FROM employee WHERE departmentid =:search", nativeQuery = true)
    public List<Employee> searchByDepartment(@Param("search") Long search);

}

