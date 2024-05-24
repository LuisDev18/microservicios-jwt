package com.example.departamentservices.service;

import com.example.departamentservices.converter.DepartmentConverter;
import com.example.departamentservices.dto.DepartmentDto;
import com.example.departamentservices.entity.Department;
import com.example.departamentservices.repository.DepartmentRepository;
import com.example.departamentservices.service.impl.DepartmentServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private DepartmentConverter departmentConverter;

    @InjectMocks
    private DepartmentServiceImpl departmentServiceImpl;

    private DepartmentDto departmentDto;
    private Department department;


    @BeforeEach
    public void setup() {

        departmentDto = new DepartmentDto();
        departmentDto.setId(1L);
        departmentDto.setDepartmentName("IT");
        departmentDto.setDepartmentDescription("IT Department");
        departmentDto.setDepartmentCode("IT-01");

        // Configurar el comportamiento del objeto simulado (departmentConverter)
        department = new Department();
        department.setId(departmentDto.getId());
        department.setDepartmentName((departmentDto.getDepartmentName()));
        department.setDepartmentDescription(departmentDto.getDepartmentDescription());
        department.setDepartmentCode(departmentDto.getDepartmentCode());

        // Configurar el comportamiento del objeto simulado para el método convertToEntity()
        Mockito.when(departmentConverter.convertToEntity(Mockito.any(DepartmentDto.class)))
                .thenReturn(department);

        // Configurar el comportamiento del objeto simulado para el método convertToDto()
        Mockito.when(departmentConverter.convertToDto(Mockito.any(Department.class)))
                .thenReturn(departmentDto);
    }

    @Test
    public void saveDepartmentOK() {
        //Given
        BDDMockito.given(departmentRepository.save(department)).willReturn(department);
        //When
        DepartmentDto responseOK = departmentServiceImpl.saveDepartment(departmentDto);
        //Then
        Assertions.assertThat(responseOK).isNotNull();
        Assertions.assertThat(responseOK.getId()).isGreaterThan(0);
    }
}
