package com.school.schoolbackend.api;

// 👇 បីបន្ទាត់នេះសំខាន់ណាស់! (Imports)
import com.school.schoolbackend.api.dto.LoginDto;
import com.school.schoolbackend.api.dto.LoginResponse;
import com.school.schoolbackend.api.dto.RegisterDto;

import com.school.schoolbackend.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    // 1. REGISTER
    @PostMapping("/register")
    public String register(@RequestBody RegisterDto dto) {
        return service.register(dto);
    }

    // 2. LOGIN (កន្លែងដែល Error មុននេះ)
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginDto dto) {
        return service.login(dto);
    }
}