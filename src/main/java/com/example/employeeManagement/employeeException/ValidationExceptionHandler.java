package com.example.employeeManagement.employeeException;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ValidationExceptionHandler {
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> notValid(MethodArgumentNotValidException exception){

        Map<String, List<String>> body = new HashMap<>();
        List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        body.put("errors", errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
