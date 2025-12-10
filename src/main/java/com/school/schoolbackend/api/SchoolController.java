package com.school.schoolbackend.api;

import com.school.schoolbackend.api.dto.SchoolDto;
import com.school.schoolbackend.service.SchoolService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/schools")
public class SchoolController {

    // 1. We name this variable "service" (singular)
    private final SchoolService service;

    public SchoolController(SchoolService service) {
        this.service = service;
    }

    @PostMapping
    public SchoolDto create(@RequestBody SchoolDto dto) {
        // 2. We use "service" here
        return service.createSchool(dto);
    }

    @GetMapping
    public List<SchoolDto> list() {
        // 3. We use "service" here too.
        // Make sure your SchoolService has the method "getAllSchools()"
        return service.getAllSchools();
    }
}