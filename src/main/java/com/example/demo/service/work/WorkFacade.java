package com.example.demo.service.work;

import com.example.demo.dto.work.request.WorkCreateRequestDto;
import com.example.demo.model.milestone.Milestone;
import com.example.demo.model.project.Project;
import com.example.demo.model.project.ProjectMember;
import com.example.demo.model.user.User;
import com.example.demo.model.work.Work;
import com.example.demo.service.milestone.MilestoneService;
import com.example.demo.service.project.ProjectMemberService;
import com.example.demo.service.project.ProjectService;
import com.example.demo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkFacade {
    private final WorkService workService;
    private final ProjectService projectService;
    private final MilestoneService milestoneService;
    private final UserService userService;
    private final ProjectMemberService projectMemberService;

    public void create(Long projectId, Long milestoneId, WorkCreateRequestDto workCreateRequestDto) {
        Project project = projectService.findById(projectId);
        Milestone milestone = milestoneService.findById(milestoneId);
        User user = userService.findById(workCreateRequestDto.getAssignedUserId());
        ProjectMember projectMember = projectMemberService.findProjectMemberByProjectAndUser(project, user);
        Work work = workCreateRequestDto.toWorkEntity(project, milestone, user, projectMember);

        workService.save(work);
    }

}