package com.school.schoolbackend.repository;

import com.school.schoolbackend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // យើងត្រូវការ Function ពិសេសមួយ ដើម្បីរក User តាមរយៈ Username ពេល Login
    Optional<User> findByUsername(String username);
}