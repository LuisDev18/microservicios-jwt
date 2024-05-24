package com.example.organizationservices.service.impl;

import com.example.organizationservices.dto.OrganizationDto;
import com.example.organizationservices.entity.Organization;
import com.example.organizationservices.mapper.OrganizationMapper;
import com.example.organizationservices.repository.OrganizationRepository;
import com.example.organizationservices.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl  implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        //Convert OrganizationDto to Organization
        Organization organization = OrganizationMapper.mapToOrganization(organizationDto);

        organizationRepository.save(organization);
        return OrganizationMapper.mapToOrganizationDto(organization);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {
        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);
        return OrganizationMapper.mapToOrganizationDto(organization);
    }


}
