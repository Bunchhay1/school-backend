package com.school.schoolbackend.api;

import com.school.schoolbackend.api.dto.StudentDto;
// 👇 Import ថ្មី (សម្រាប់មើលបញ្ជីសិស្ស)
import com.school.schoolbackend.api.dto.StudentResponseDto;
import com.school.schoolbackend.service.StudentService;
import org.springframework.web.bind.annotation.*;

// 👇 Import សម្រាប់ List
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // 1. CREATE (បង្កើតសិស្ស)
    @PostMapping
    public String createStudent(@RequestBody StudentDto dto) {
        return service.createStudent(dto);
    }

    // 2. GET ALL (មើលសិស្សទាំងអស់) - កន្លែងដែល Error មុននេះ
    @GetMapping
    public List<StudentResponseDto> getAll() {
        return service.getAllStudents();
    }
}