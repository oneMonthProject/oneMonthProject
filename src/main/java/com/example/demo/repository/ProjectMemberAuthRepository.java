package com.example.demo.repository;

import com.example.demo.model.ProjectMemberAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectMemberAuthRepository extends JpaRepository<ProjectMemberAuth, Long> {
    Optional<ProjectMemberAuth> findTopByOrderByIdDesc();
}
