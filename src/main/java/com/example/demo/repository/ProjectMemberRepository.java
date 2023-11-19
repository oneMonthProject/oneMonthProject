package com.example.demo.repository;

import com.example.demo.model.Project;
import com.example.demo.model.ProjectMember;
import com.example.demo.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {
    Optional<List<ProjectMember>> findProjectsMemberByProject(Project project);

    Optional<ProjectMember> findProjectMemberByProjectAndUser(Project project, User user);
}
