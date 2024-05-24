package com.example.employeeservices.converter;

import com.example.employeeservices.dto.EmployeeDto;
import com.example.employeeservices.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter extends AbstractConverter<Employee, EmployeeDto>{

    @Override
    public EmployeeDto toDto(Employee entity) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(entity.getId());
        employeeDto.setFirstName(entity.getFirstName());
        employeeDto.setLastName(entity.getLastName());
        employeeDto.setEmail(entity.getEmail());
        employeeDto.setDepartmentCode(entity.getDepartmentCode());
        employeeDto.setOrganizationCode(entity.getOrganizationCode());
        return employeeDto;
    }

    @Override
    public Employee toEntity(EmployeeDto dto) {
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setEmail(dto.getEmail());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setDepartmentCode(dto.getDepartmentCode());
        employee.setOrganizationCode(dto.getOrganizationCode());
        return employee;
    }
}
