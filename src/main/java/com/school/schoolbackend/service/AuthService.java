package com.school.schoolbackend.service;

import com.school.schoolbackend.api.dto.LoginDto;
import com.school.schoolbackend.api.dto.LoginResponse;
import com.school.schoolbackend.api.dto.RegisterDto;
import com.school.schoolbackend.config.JwtUtils;
import com.school.schoolbackend.domain.User;
import com.school.schoolbackend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils; // <--- បន្ថែមថ្មី

    public AuthService(UserRepository repository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    // REGISTER (ដដែល)
    public String register(RegisterDto dto) {
        User user = new User();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRole(dto.role());
        repository.save(user);
        return "User created successfully";
    }

    // LOGIN (ថ្មី!)
    public LoginResponse login(LoginDto dto) {
        // 1. រក User តាម Username
        User user = repository.findByUsername(dto.username())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. ផ្ទៀងផ្ទាត់ Password (យក 123 ទៅធៀបនឹង $2a$10...)
        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        // 3. បង្កើត Token ដែលមាន Role
        String token = jwtUtils.generateToken(user.getUsername(), user.getRole().name());

        // 4. ឆ្លើយតបទៅវិញ
        return new LoginResponse(token, user.getRole().name());
    }
}