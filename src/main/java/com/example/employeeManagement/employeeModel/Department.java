package com.example.employeeManagement.employeeModel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "department")
public class Department {

    @Id
    @Column(name = "departmentid")
    private long departmentId;

    @Column(name = "departmentname")
    private String departmentName;

}
