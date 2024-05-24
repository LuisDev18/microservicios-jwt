package com.example.departamentservices.converter;

import com.example.departamentservices.dto.DepartmentDto;
import com.example.departamentservices.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentConverter extends AbstractConvertert<Department, DepartmentDto>{

    @Override
    public Department convertToEntity(DepartmentDto dto) {
        Department department = new Department();
        department.setId(dto.getId());
        department.setDepartmentName(dto.getDepartmentName());
        department.setDepartmentDescription(dto.getDepartmentDescription());
        department.setDepartmentCode(dto.getDepartmentCode());
        return department;
    }

    @Override
    public DepartmentDto convertToDto(Department entity) {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(entity.getId());
        departmentDto.setDepartmentName(entity.getDepartmentName());
        departmentDto.setDepartmentDescription(entity.getDepartmentDescription());
        departmentDto.setDepartmentCode(entity.getDepartmentCode());
        return departmentDto;
    }
}
