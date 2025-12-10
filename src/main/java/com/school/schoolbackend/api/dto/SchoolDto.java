package com.school.schoolbackend.api.dto;

import java.time.LocalDateTime;

public record SchoolDto(
        String id,
        String name,
        String code,
        String address,
        String contactEmail,
        String logoUrl,
        LocalDateTime createdAt
) {}