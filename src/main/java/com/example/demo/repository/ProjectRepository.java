package com.example.demo.repository;

import com.example.demo.model.Project;
import java.util.List;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByUser(User user);
}
