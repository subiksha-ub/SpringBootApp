package com.example.employeeManagement.employeeModel;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long employeeId;

    @NotBlank(message = "First name cannot be empty")
    @Column(name = "firstname")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    @Column(name = "lastname")
    private String lastName;

    @NotBlank(message = "Email must be valid")
    @Column(name = "email")
    private String email;

//    @Pattern(regexp = "^{10}$")
    @Column(name = "phonenumber")
    private long phoneNumber;

    @Column(name = "countrycode")
    private String countryCode;

    @Column(name = "departmentid")
    private long departmentId;

    @Column(name = "createddate")
    private Date createdDate;

    @Column(name = "updateddate")
    private Date updatedDate;

}
