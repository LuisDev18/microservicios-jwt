package com.example.employeeservices.helper;

import com.example.employeeservices.dto.DepartmentDto;
import com.example.employeeservices.dto.OrganizationDto;
import com.example.employeeservices.util.Constant;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Slf4j
public class ExternalServiceClient {

    private final WebClient webClient;

    public ExternalServiceClient(WebClient webClient) {
        this.webClient = webClient;
    }

    @CircuitBreaker(name = "deptService", fallbackMethod = "getFallbackDept")
    public DepartmentDto getDepartment(String code) {
        return webClient.get()
                .uri(Constant.DEPARTMENT_BASE_URL + code)
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();
    }

    @CircuitBreaker(name = "orgService", fallbackMethod = "getFallbackOrg")
    public OrganizationDto getOrganization(String code) {
        return webClient.get()
                .uri(Constant.ORGANIZATION_BASE_URL + "/msvc/" + code)
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();
    }

    // Fallbacks específicos
    public DepartmentDto getFallbackDept(String code, Throwable e) {
        log.warn("Msvc Departamentos falló para código {}. Motivo: {}", code, e.getMessage());
        return new DepartmentDto();
    }

    public OrganizationDto getFallbackOrg(String code, Throwable e) {
        log.warn("Msvc Organizaciones falló para código {}. Motivo: {}", code, e.getMessage());
        return new OrganizationDto();
    }
}




