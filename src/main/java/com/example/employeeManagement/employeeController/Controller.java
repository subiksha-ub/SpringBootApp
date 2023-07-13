package com.example.employeeManagement.employeeController;
import com.example.employeeManagement.CacheConfig.RedisClient;
import com.example.employeeManagement.Services.Services;
import com.example.employeeManagement.employeeException.EmployeeNotFoundException;
import com.example.employeeManagement.employeeModel.Employee;
import com.example.employeeManagement.employeeRepository.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/poc/api/employee-management/")
public class Controller {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    Services services;

    @Autowired
    RedisClient redisClient;

    @Autowired
    RedisTemplate<String,String> redisTemplate;


    @PostMapping
    public ResponseEntity<?> createEmployees(@Valid @RequestBody Employee employee) throws ExecutionException, InterruptedException {
        Date currentDate = new Date();
        services.checkCache();
        employee.setCountryCode(redisClient.getKey(employee.getCountryCode()));
        employee.setCreatedDate(currentDate);
        employeeRepository.save(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping
    public List<Employee> fetchEmployees() {
        return employeeRepository.findAll();
    }

    @PutMapping("/empId/{id}")
    public Employee updateEmployees(@PathVariable("id") long id, @RequestBody Employee employee) {
        if (employeeRepository.findById(id).isEmpty())
            throw new EmployeeNotFoundException("Cannot find employee");
        Employee updateEmployee = employeeRepository.findById(id).orElseThrow();
        Date currentDate = new Date();
        updateEmployee.setEmployeeId(id);
        updateEmployee.setFirstName(employee.getFirstName());
        updateEmployee.setLastName((employee.getLastName()));
        updateEmployee.setEmail(employee.getEmail());
        updateEmployee.setDepartmentId(employee.getDepartmentId());
        updateEmployee.setCountryCode(redisClient.getKey(employee.getCountryCode()));
        updateEmployee.setPhoneNumber(employee.getPhoneNumber());
        updateEmployee.setCreatedDate(updateEmployee.getCreatedDate());
        updateEmployee.setUpdatedDate(currentDate);
        return employeeRepository.save(updateEmployee);
    }

    @GetMapping("/empId/{id}")
    public Optional<Employee> findEmployeeByEmpId(@PathVariable("id") long id) {
        if (employeeRepository.findById(id).isEmpty())
            throw new EmployeeNotFoundException("Cannot find employee");
        return employeeRepository.findById(id);
    }

    @DeleteMapping("/empId/{id}")
    public ResponseEntity<Employee> deleteEmployeeByEmpId(@PathVariable("id") long id) {
        if (employeeRepository.findById(id).isEmpty())
            throw new EmployeeNotFoundException("Cannot find employee");
        Employee employee = employeeRepository.findById(id).orElseThrow();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/searchName/{search}")
    public List<Employee> findEmployeeByName(@PathVariable("search") String search) {
        return employeeRepository.searchByName(search);
    }

    @GetMapping("/searchDeptId/{id}")
    public List<Employee> findEmployeeByDeptId(@PathVariable("id") long id) {
        if (employeeRepository.findById(id).isEmpty())
            throw new EmployeeNotFoundException("Cannot find employee");
        return employeeRepository.searchByDepartment(id);
    }

    @GetMapping("external/{country}")
    public String external(@PathVariable("country") String country) {
        services.checkCache();
        return redisClient.getKey(country);
    }
//@GetMapping("ISO/{name}")
//    public Mono<String> externalApi(@PathVariable("name") String name) {
//        return services.callExternalApi()
//                .flatMapMany(Flux::fromIterable)
//                .map(obj -> (Map<?, ?>) obj)
//                .filter(map -> name.equalsIgnoreCase((String)map.get("name")))
//                .map(map -> (String) map.get("Iso3"))
//                .single()
//                .doOnNext(s -> System.out.println("Value: " + s));
//
//    }
}

//redis