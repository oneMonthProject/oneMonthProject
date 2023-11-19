package com.example.demo.repository;

import com.example.demo.model.Milestone;
import com.example.demo.model.Project;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MileStoneRepository extends JpaRepository<Milestone, Long> {
    Optional<List<Milestone>> findMilestonesByProject(Project project);
}
