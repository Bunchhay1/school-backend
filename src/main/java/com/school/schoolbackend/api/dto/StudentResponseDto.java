package com.school.schoolbackend.api.dto;

public record StudentResponseDto(
        String id,
        String firstName,
        String lastName,
        String gender,
        String schoolName, // យើងបង្ហាញឈ្មោះសាលា ជំនួសឱ្យ ID
        String username    // យើងបង្ហាញ Username របស់សិស្ស
) {}