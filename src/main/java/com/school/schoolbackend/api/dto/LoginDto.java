package com.school.schoolbackend.api.dto;

import com.school.schoolbackend.domain.Role;

public record LoginDto(String username, String password) {
    public static record RegisterDto(
            String username,
            String password,
            Role role // ADMIN, TEACHER, or STUDENT
    ) {}
}