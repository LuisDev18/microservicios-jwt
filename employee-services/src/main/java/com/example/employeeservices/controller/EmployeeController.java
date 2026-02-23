package com.example.employeeservices.controller;

import com.example.employeeservices.dto.ApiResponseDto;
import com.example.employeeservices.dto.EmployeeDto;
import com.example.employeeservices.service.EmployeeService;
import com.example.employeeservices.util.WrapperResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@Tag(name = "Employee Controller", description = "This controller is used to perform CRUD operations on Employee")
@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @Operation(summary = "Save Employee", description = "This API is used to save employee")
    @ApiResponse(responseCode = "201", description = "Employee saved successfully")
    @PostMapping(consumes= "application/json",produces = "application/json")
    public ResponseEntity<WrapperResponse<EmployeeDto>> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto employeeDtoResponse = employeeService.save(employeeDto);
        return new WrapperResponse<>(employeeDtoResponse, "success", Instant.now(), "Employee saved successfully").createResponse(HttpStatus.CREATED);
    }

    @Operation(summary = "Get Employee By Id", description = "This API is used to get employee by id")
    @ApiResponse(responseCode = "200", description = "Employee retrieved successfully")
    @GetMapping(value="/{id}", produces = "application/json")
    public ResponseEntity<WrapperResponse<ApiResponseDto>> getEmployeeById(@PathVariable("id") Long id) {
        ApiResponseDto employeeDto = employeeService.getEmployeeById(id);
        return new WrapperResponse<>(employeeDto, "success", Instant.now(), "Employee retrieved successfully").createResponse(HttpStatus.OK);
    }


}
