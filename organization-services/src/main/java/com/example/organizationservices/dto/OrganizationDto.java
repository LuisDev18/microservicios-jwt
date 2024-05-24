package com.example.organizationservices.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Schema(name = "OrganizationDto", description = "OrganizationDto")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDto {

    private Long id;

    @Schema(description = "Organization name", example = "HTF")
    private String organizationName;

    @Schema(description = "Organization description", example = "Guild pvp")
    private String organizationDescription;

    @Schema(description = "Organization code", example = "HTF-001")
    private String organizationCode;


    private LocalDateTime createDate;
}
