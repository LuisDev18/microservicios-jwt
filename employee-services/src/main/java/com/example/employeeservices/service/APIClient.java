package com.example.employeeservices.service;

import com.example.employeeservices.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(url="localhost:8082/api/v1", value="DEPARTMENT-SERVICE")
public interface APIClient {

    @GetMapping("/departments/{department-code}")
    DepartmentDto getDepartment(@PathVariable("department-code") String deparmentCode);
}




