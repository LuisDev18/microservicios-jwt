package com.example.departamentservices.controller;

import com.example.departamentservices.dto.DepartmentDto;
import com.example.departamentservices.service.DepartmentService;
import com.example.departamentservices.util.WrapperResponse;
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

@Tag(   name = "Department Service",
        description = "Department API")

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @Operation(summary = "Save a department",
    description = "Save a department object in the database")
    @ApiResponse(responseCode = "201", description = "Department saved successfully")
    @PostMapping(produces="application/json",consumes = "application/json")
    public ResponseEntity<WrapperResponse<DepartmentDto>> saveDepartment(@RequestBody DepartmentDto departmentDto) {
        DepartmentDto departmentResponse=departmentService.saveDepartment(departmentDto);
        return new WrapperResponse<>(departmentResponse,"CREATED", Instant.now(),"Department saved successfully").createResponse(HttpStatus.CREATED);
    }

    @Operation(summary = "Get a department",
            description = "Get a department object from the database")
    @GetMapping(value="/{department-code}")
   public ResponseEntity<DepartmentDto> getDepartment(@PathVariable ("department-code") String deparmentCode){
        DepartmentDto departmentDto=departmentService.getDepartmentByCode(deparmentCode);
        return new ResponseEntity<>(departmentDto,HttpStatus.OK);
    }

}
