package com.school.schoolbackend.repository;

import com.school.schoolbackend.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    // មុខងារពិសេស: ទាញយកសិស្សទាំងអស់ ដែលរៀននៅសាលាណាមួយ
    // SELECT * FROM students WHERE school_id = ?
    List<Student> findBySchoolId(String schoolId);
}