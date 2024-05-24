package com.example.departamentservices.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(name = "DepartmentDto", description = "DepartmentDto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    private Long id;

    @Schema(description = "Department name", example = "Bella Vista")
    private String departmentName;

    @Schema(description = "Department description", example = "4 habitaciones, 2 baños, 1 cocina, 1 sala")
    private String departmentDescription;

    @Schema(description = "Department code", example = "BV-001")
    private String departmentCode;
}
