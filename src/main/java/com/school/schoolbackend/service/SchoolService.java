package com.school.schoolbackend.service;

import com.school.schoolbackend.api.dto.SchoolDto;
import com.school.schoolbackend.domain.School;
import com.school.schoolbackend.repository.SchoolRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    private final SchoolRepository repository;

    public SchoolService(SchoolRepository repository) {
        this.repository = repository;
    }

    // 1. CREATE
    @Transactional
    public SchoolDto createSchool(SchoolDto dto) {
        School school = new School();
        school.setName(dto.name());
        school.setCode(dto.code());
        school.setAddress(dto.address());
        school.setContactEmail(dto.contactEmail());
        school.setLogoUrl(dto.logoUrl());

        School savedSchool = repository.save(school);
        return mapToDto(savedSchool);
    }

    // 2. GET ALL (This is the method you were missing!)
    @Transactional(readOnly = true)
    public List<SchoolDto> getAllSchools() {
        return repository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Helper: Map Entity -> DTO
    private SchoolDto mapToDto(School school) {
        return new SchoolDto(
                school.getId(),
                school.getName(),
                school.getCode(),
                school.getAddress(),
                school.getContactEmail(),
                school.getLogoUrl(),
                school.getCreatedAt()
        );
    }
}