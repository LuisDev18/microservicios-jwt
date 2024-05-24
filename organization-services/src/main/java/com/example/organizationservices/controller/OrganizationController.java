package com.example.organizationservices.controller;

import com.example.organizationservices.dto.OrganizationDto;
import com.example.organizationservices.service.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.organizationservices.util.WrapperResponse;

@Tag( name = "Organization Service",
        description = "Organization API")
@RestController
@RequestMapping("/organization")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @Operation(summary = "Save a organization",
    description = "Save a organization object in the database")
    @ApiResponse(responseCode = "201", description = "Organization saved successfully")
    @PostMapping( consumes = "application/json", produces = "application/json")
    public ResponseEntity<WrapperResponse<OrganizationDto>> saveOrganization(@RequestBody OrganizationDto organizationDto){
        OrganizationDto organizationResponse=organizationService.saveOrganization(organizationDto);
        return new WrapperResponse<>(organizationResponse, "ok", "Organization saved successfully").createResponse(HttpStatus.CREATED);
    }

    @Operation(summary = "Get a organization",
            description = "Get a organization object in the database")
    @GetMapping(name="/msvc/{Code}", produces="application/json")
    public ResponseEntity<OrganizationDto> getOrganizationWebClient(@PathVariable(value= "Code") String organizationCode){
        OrganizationDto organizationResponse = organizationService.getOrganizationByCode(organizationCode);
        return new ResponseEntity<>(organizationResponse,HttpStatus.OK);
    }

   /* @GetMapping(name="/{Code}", produces = "application/json")
    public ResponseEntity<WrapperResponse<OrganizationDto>> getOrganization(@PathVariable(value= "Code") String organizationCode){
        OrganizationDto organizationResponse = organizationService.getOrganizationByCode(organizationCode);
        return new WrapperResponse<>(organizationResponse,"ok", "Organization saved successfully").createResponse(HttpStatus.CREATED);
    }*/

}
