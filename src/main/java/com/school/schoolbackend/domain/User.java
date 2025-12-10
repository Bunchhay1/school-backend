package com.school.schoolbackend.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "users") // ឈ្មោះ Table ក្នុង MySQL
@Data // បង្កើត Getters/Setters ដោយស្វ័យប្រវត្តិ
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // ប្រើ UUID ដូច School ដែរ ដើម្បីសុវត្ថិភាព
    private String id;

    @Column(nullable = false, unique = true)
    private String username; // ឈ្មោះសម្រាប់ Login

    @Column(nullable = false)
    private String password; // លេខសម្ងាត់ (នឹងត្រូវ Hash ពេលក្រោយ)

    @Enumerated(EnumType.STRING) // រក្សាទុកជាអក្សរ "ADMIN" ជំនួសឱ្យលេខ 0, 1
    @Column(nullable = false)
    private Role role;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}