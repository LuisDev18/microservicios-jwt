package com.example.employeeservices.controller;

import com.example.employeeservices.dto.EmployeeDto;
import com.example.employeeservices.service.EmployeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
@ActiveProfiles("test")
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    @DisplayName("Test Employee save success")
    public void saveEmployeeTest() throws Exception {
        //BEGIN
         String employeeDto= """
                 {
                     "firstName": "John",
                     "lastName": "Doe",
                     "email": "john.doe@example.com"                
                 }
                 """;

        BDDMockito.given(employeeService.save(ArgumentMatchers.any(EmployeeDto.class)))
                .willAnswer((invocation)->{
                    EmployeeDto dtoResponse = (EmployeeDto) invocation.getArgument(0);
                    dtoResponse.setId(1l);
                    dtoResponse.setFirstName("John");
                    dtoResponse.setLastName("Doe");
                    dtoResponse.setEmail("john.doe@example.com");
                    return dtoResponse;
                });

        //WHEN
      ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON).content(employeeDto));
        //THEN
        response.andDo(MockMvcResultHandlers.print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.firstName").value("John"))
        .andExpect(jsonPath("$.data.lastName").value("Doe"))
        .andExpect(jsonPath("$.data.id").value(1l))
        .andExpect(jsonPath("$.data.email").value("john.doe@example.com"));
    }
}
