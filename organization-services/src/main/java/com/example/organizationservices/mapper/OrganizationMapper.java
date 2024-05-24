package com.example.organizationservices.mapper;

import com.example.organizationservices.dto.OrganizationDto;
import com.example.organizationservices.entity.Organization;

public class OrganizationMapper {

    public static OrganizationDto mapToOrganizationDto(Organization organization) {
        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setId(organization.getId());
        organizationDto.setOrganizationName(organization.getOrganizationName());
        organizationDto.setOrganizationDescription(organization.getOrganizationDescription());
        organizationDto.setOrganizationCode(organization.getOrganizationCode());
        organizationDto.setCreateDate(organization.getCreateDate());
        return organizationDto;
    }

    public static Organization mapToOrganization(OrganizationDto organizationDto) {
        Organization organization = new Organization();
        organization.setId(organizationDto.getId());
        organization.setOrganizationName(organizationDto.getOrganizationName());
        organization.setOrganizationDescription(organizationDto.getOrganizationDescription());
        organization.setOrganizationCode(organizationDto.getOrganizationCode());
        organization.setCreateDate(organizationDto.getCreateDate());
        return organization;
    }
}
