package com.school.schoolbackend.api.dto;

import java.time.LocalDate;

public record StudentDto(
        // Part 1: Login Info
        String username,
        String password,

        // Part 2: Personal Info
        String firstName,
        String lastName,
        String gender,
        LocalDate dateOfBirth,

        // Part 3: Link to School
        String schoolId
) {}