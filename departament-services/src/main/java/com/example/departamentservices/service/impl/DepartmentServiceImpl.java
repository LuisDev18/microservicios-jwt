package com.example.departamentservices.service.impl;

import com.example.departamentservices.converter.DepartmentConverter;
import com.example.departamentservices.dto.DepartmentDto;
import com.example.departamentservices.entity.Department;
import com.example.departamentservices.exception.ResourceNotFoundException;
import com.example.departamentservices.repository.DepartmentRepository;
import com.example.departamentservices.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentConverter departmentConverter;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
      Department department = departmentConverter.convertToEntity(departmentDto);
      departmentRepository.save(department);
      return departmentConverter.convertToDto(department);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
      Department department = departmentRepository.findByDepartmentCode(code);
      if(department == null){
        throw new ResourceNotFoundException("Department","code",code);
      }
      return departmentConverter.convertToDto(department);
    }
}
