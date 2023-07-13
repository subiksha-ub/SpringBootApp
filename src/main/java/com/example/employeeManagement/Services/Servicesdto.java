package com.example.employeeManagement.Services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Servicesdto {
    private boolean error;

    private String msg;

    private List<Countries> data;

}

