package com.example.employeeservices.service;

import com.example.employeeservices.dto.ApiResponseDto;
import com.example.employeeservices.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto save (EmployeeDto employeeDto);
    ApiResponseDto getEmployeeById(Long id);
}
