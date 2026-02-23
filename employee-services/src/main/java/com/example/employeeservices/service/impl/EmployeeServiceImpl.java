package com.example.employeeservices.service.impl;

import com.example.employeeservices.converter.EmployeeConverter;
import com.example.employeeservices.dto.ApiResponseDto;
import com.example.employeeservices.dto.DepartmentDto;
import com.example.employeeservices.dto.EmployeeDto;
import com.example.employeeservices.dto.OrganizationDto;
import com.example.employeeservices.entity.Employee;
import com.example.employeeservices.exception.ResourceNotFoundException;
import com.example.employeeservices.helper.ExternalServiceClient;
import com.example.employeeservices.repository.EmployeeRepository;
import com.example.employeeservices.service.APIClient;
import com.example.employeeservices.service.EmployeeService;
import com.example.employeeservices.util.Constant;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeRepository employeeRepository;
    private final EmployeeConverter employeeConverter;
    private final ExternalServiceClient externalServiceClient;
    //private final APIClient apiClient;


    @Override
    public EmployeeDto save(EmployeeDto employee) {
        Employee employeeDb = employeeConverter.toEntity(employee);
        employeeRepository.save(employeeDb);

        return employeeConverter.toDto(employeeDb);
    }

    @Override
    public ApiResponseDto getEmployeeById(Long id) {
        log.info("Iniciando flujo de empleado: {}", id);

        var employee = findEmployeeById(id);
        DepartmentDto departmentDto = externalServiceClient.getDepartment(employee.getDepartmentCode());
        OrganizationDto organizationDto = externalServiceClient.getOrganization(employee.getOrganizationCode());

        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setEmployeeDto(employeeConverter.toDto(employee));
        apiResponseDto.setDepartmentDto(departmentDto);
        apiResponseDto.setOrganizationDto(organizationDto);

        return apiResponseDto;
    }

    private Employee findEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

    }

}
