package com.example.employeeservices.service;

import com.example.employeeservices.converter.EmployeeConverter;
import com.example.employeeservices.dto.ApiResponseDto;
import com.example.employeeservices.dto.DepartmentDto;
import com.example.employeeservices.dto.EmployeeDto;
import com.example.employeeservices.dto.OrganizationDto;
import com.example.employeeservices.entity.Employee;
import com.example.employeeservices.exception.ResourceNotFoundException;
import com.example.employeeservices.helper.ExternalServiceClient;
import com.example.employeeservices.repository.EmployeeRepository;
import com.example.employeeservices.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {


    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private EmployeeConverter employeeConverter;
    @Mock
    private ExternalServiceClient externalServiceClient;

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;

    @Test
    public void getEmployeeByIdTestSuccess(){
        //GIVEN
        Long employeeId = 1L;
        Employee employee = createEmployee(employeeId);
        EmployeeDto employeeDto = createEmployeeDto(employeeId);
        DepartmentDto departmentDto = createDepartmentDto("Dp-502");
        OrganizationDto organizationDto = createOrganizationDto("Or-502");

        BDDMockito.given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));
        BDDMockito.given(employeeConverter.toDto(employee)).willReturn(employeeDto);
        BDDMockito.given(externalServiceClient.getDepartment("Dp-502")).willReturn(departmentDto);
        BDDMockito.given(externalServiceClient.getOrganization("Or-502")).willReturn(organizationDto);

        //WHEN
        ApiResponseDto response = employeeServiceImpl.getEmployeeById(1L);

        //THEN
        Assertions.assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(employeeDto, response.getEmployeeDto()),
                () -> verify(employeeRepository).findById(employeeId),
                () -> verify(externalServiceClient).getDepartment("Dp-502")
        );
    }


    @Test
    public void getEmployeeByIdTestFail(){
        //GIVEN
        BDDMockito.given(employeeRepository.findById(1L)).willReturn(Optional.empty());
        //WHEN
        Assertions.assertThrows(ResourceNotFoundException.class,()->{
            employeeServiceImpl.getEmployeeById(1L);
        });

    }

    private Employee createEmployee(Long id) {
        return Employee.builder()
                .id(id)
                .firstName("Pepe")
                .lastName("Simpson")
                .departmentCode("Dp-502")
                .organizationCode("Or-502")
                .build();
    }

    private EmployeeDto createEmployeeDto(Long id) {
        return EmployeeDto.builder()
                .id(id)
                .firstName("Pepe")
                .lastName("Simpson")
                .build();
    }

    private DepartmentDto createDepartmentDto(String code) {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("Las Gaviotas");
        departmentDto.setDepartmentCode("Dp-502");
        departmentDto.setDepartmentDescription("4habitaciones 2baños");
        return departmentDto;
    }

    private OrganizationDto createOrganizationDto(String code) {
        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setOrganizationCode("Or-502");
        organizationDto.setOrganizationName("Las Gaviotas");
        return organizationDto;
    }

}
