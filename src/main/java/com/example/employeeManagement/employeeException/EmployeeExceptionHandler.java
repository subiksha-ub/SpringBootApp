package com.example.employeeManagement.employeeException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
//
//import static java.util.stream.Nodes.collect;


@ControllerAdvice
public class EmployeeExceptionHandler extends ResponseEntityExceptionHandler{


    @ExceptionHandler(value = {EmployeeNotFoundException.class})
    public ResponseEntity<EmployeeException>
    employeeExceptionHandler(EmployeeNotFoundException employeeNotFoundException){
        EmployeeException employeeException = new EmployeeException(
                employeeNotFoundException.getMessage(),
                employeeNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(employeeException,HttpStatus.NOT_FOUND);
    }
}
