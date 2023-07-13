package com.example.employeeManagement.employeeException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@Getter
@Setter
public class EmployeeException {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;
}
