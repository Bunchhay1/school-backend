package com.school.schoolbackend.service;

import com.school.schoolbackend.api.dto.StudentDto;
// 👇 Import ថ្មីដែលបងខ្វះ
import com.school.schoolbackend.api.dto.StudentResponseDto;

import com.school.schoolbackend.domain.Role;
import com.school.schoolbackend.domain.School;
import com.school.schoolbackend.domain.Student;
import com.school.schoolbackend.domain.User;
import com.school.schoolbackend.repository.SchoolRepository;
import com.school.schoolbackend.repository.StudentRepository;
import com.school.schoolbackend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 👇 Import សម្រាប់ List និង Collectors
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final SchoolRepository schoolRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentService(StudentRepository studentRepository,
                          UserRepository userRepository,
                          SchoolRepository schoolRepository,
                          PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.schoolRepository = schoolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 1. CREATE STUDENT
    @Transactional
    public String createStudent(StudentDto dto) {
        School school = schoolRepository.findById(dto.schoolId())
                .orElseThrow(() -> new RuntimeException("School not found"));

        User user = new User();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRole(Role.STUDENT);

        User savedUser = userRepository.save(user);

        Student student = new Student();
        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setGender(dto.gender());
        student.setDateOfBirth(dto.dateOfBirth());

        student.setUser(savedUser);
        student.setSchool(school);

        studentRepository.save(student);

        return "Student created successfully: " + student.getFirstName();
    }

    // 2. GET ALL STUDENTS (កន្លែងដែល Error មុននេះ)
    @Transactional(readOnly = true)
    public List<StudentResponseDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Helper: Map Entity -> Response DTO
    private StudentResponseDto mapToDto(Student student) {
        return new StudentResponseDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getGender(),
                student.getSchool().getName(), // បង្ហាញឈ្មោះសាលា
                student.getUser().getUsername() // បង្ហាញ Username
        );
    }
}