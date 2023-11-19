package com.example.demo.repository;

import com.example.demo.model.Milestone;
import com.example.demo.model.Project;
import com.example.demo.model.Work;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkRepository extends JpaRepository<Work, Long> {
    Optional<List<Work>> findWorksByProject(Project project);

    Optional<List<Work>> findWorksByProjectAndMilestone(Project project, Milestone milestone);
}
