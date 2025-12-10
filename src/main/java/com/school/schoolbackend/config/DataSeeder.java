package com.school.schoolbackend.config;

import com.school.schoolbackend.domain.Role;
import com.school.schoolbackend.domain.User;
import com.school.schoolbackend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner initData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // 1. ឆែកមើលថាតើមាន Admin ឬនៅ?
            if (userRepository.findByUsername("admin").isEmpty()) {

                // 2. បើអត់មាន -> បង្កើត Admin មួយឈ្មោះ "admin" / password "123"
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("123")); // Hash Password

                // ⚠️ ត្រូវប្រាកដថាបងប្រើ Role ឱ្យត្រូវនឹង Enum របស់បង (SCHOOL_ADMIN ឬ ADMIN)
                admin.setRole(Role.SCHOOL_ADMIN);

                userRepository.save(admin);
                System.out.println("✅ SUPER ADMIN CREATED: username='admin', password='123'");
            }
        };
    }
}