package com.school.schoolbackend.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "students")
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String gender; // Male, Female

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    // 🔗 RELATIONSHIP 1: សិស្សម្នាក់ ត្រូវតែមានគណនីមួយ (User)
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // 🔗 RELATIONSHIP 2: សិស្សជាច្រើន រៀននៅសាលាតែមួយ (School)
    @ManyToOne
    @JoinColumn(name = "school_id", referencedColumnName = "id")
    private School school;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}