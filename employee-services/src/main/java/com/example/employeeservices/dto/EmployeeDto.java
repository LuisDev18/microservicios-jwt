package com.example.employeeservices.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Employee Data Transfer Object")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDto {
    private Long id;

    @Schema(description = "Employee First Name", example = "John")
    private String firstName;

    @Schema(description = "Employee Last Name", example = "De la Torre")
    private String lastName;

    @Schema(description = "Employee Email", example = "luisdcm_18@gmail.com")
    private String email;

    private String departmentCode;

    private String organizationCode;
}
