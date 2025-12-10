package com.school.schoolbackend.api.dto;

import com.school.schoolbackend.domain.Role; // Import Role របស់បង

public record RegisterDto(
        String username,
        String password,
        Role role // ADMIN, TEACHER, or STUDENT
) {}