package com.example.employeeservices.service.impl;

import com.example.employeeservices.converter.EmployeeConverter;
import com.example.employeeservices.dto.ApiResponseDto;
import com.example.employeeservices.dto.DepartmentDto;
import com.example.employeeservices.dto.EmployeeDto;
import com.example.employeeservices.dto.OrganizationDto;
import com.example.employeeservices.entity.Employee;
import com.example.employeeservices.exception.ResourceNotFoundException;
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
    //private final RestTemplate restTemplate;
    private final WebClient webClient;
   // private final APIClient apiClient;


    @Override
    public EmployeeDto save(EmployeeDto employee) {
        Employee employeeDb = employeeConverter.toEntity(employee);
        employeeRepository.save(employeeDb);

        return employeeConverter.toDto(employeeDb);
    }

    //@CircuitBreaker(name = "${spring.application.name", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public ApiResponseDto getEmployeeById(Long id) {

       log.info("Inside getEmployeeById() method");
       Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", id));

      // Ejemplo de uso de WebClient

        DepartmentDto departmentDto =   webClient.get().
                uri(Constant.DEPARTMENT_BASE_URL + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

        //Ejemplo usando Feign Client
        //DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

        OrganizationDto organizationDto = webClient.get()
                .uri(Constant.ORGANIZATION_BASE_URL +"/msvc/"+ employee.getOrganizationCode())
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();

        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setDepartmentDto(departmentDto);
        apiResponseDto.setOrganizationDto(organizationDto);
        apiResponseDto.setEmployeeDto(employeeConverter.toDto(employee));

        return apiResponseDto;
    }

    public ApiResponseDto getDefaultDepartment(Long id, Exception e){
        log.info("Inside getDefaultDepartment() method");
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", id));

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentCode("RBD1");
        departmentDto.setDepartmentName("Default Department");
        departmentDto.setDepartmentDescription("Default Department Description");


        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setDepartmentDto(departmentDto);
        apiResponseDto.setEmployeeDto(employeeConverter.toDto(employee));

        return apiResponseDto;
    }
}
   /* Ejemplo de uso de RestTemplate
     ResponseEntity<DepartmentDto> departmentDtoResponseEntity =  restTemplate.getForEntity("http://localhost:8082/api/v1/departments/" + employee.getDepartmentCode(), DepartmentDto.class);

      log.info("http://localhost:8082/api/v1/departments/" + employee.getDepartmentCode());
      log.info(departmentDtoResponseEntity.getBody().toString());
      DepartmentDto departmentDto = departmentDtoResponseEntity.getBody();*/