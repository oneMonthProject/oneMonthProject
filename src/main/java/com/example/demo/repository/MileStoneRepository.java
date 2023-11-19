package com.example.demo.repository;

import com.example.demo.model.Milestone;
import com.example.demo.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MileStoneRepository extends JpaRepository<Milestone, Long> {
    Optional<List<Milestone>> findMilestonesByProject(Project project);
}
