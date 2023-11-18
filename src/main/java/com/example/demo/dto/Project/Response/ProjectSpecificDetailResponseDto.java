package com.example.demo.dto.Project.Response;

import com.example.demo.constant.ProjectStatus;
import com.example.demo.dto.ProjectMember.Response.ProjectMemberDetailResponseDto;
import com.example.demo.dto.TrustGrade.TrustGradeDto;
import com.example.demo.dto.User.Response.UserProjectDetailResponseDto;
import com.example.demo.dto.User.Response.UserProjectResponseDto;
import com.example.demo.dto.Work.Response.WorkProjectDetailResponseDto;
import com.example.demo.model.Project;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ProjectSpecificDetailResponseDto {
    private Long projectId;
    private String name;
    private String subject;
    private TrustGradeDto trustGrade;
    private UserProjectResponseDto user;
    private List<ProjectMemberDetailResponseDto> members;
    private List<WorkProjectDetailResponseDto> works;
    private ProjectStatus status;
    private int crewNumber;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public static ProjectSpecificDetailResponseDto of(
            Project project,
            TrustGradeDto trustGradeDto,
            UserProjectResponseDto userProjectResponseDto,
            List<ProjectMemberDetailResponseDto> projectMemberDetailResponseDtos,
            List<WorkProjectDetailResponseDto> workProjectDetailResponseDto

    ) {
        return ProjectSpecificDetailResponseDto.builder()
                .projectId(project.getId())
                .name(project.getName())
                .subject(project.getSubject())
                .trustGrade(trustGradeDto)
                .user(userProjectResponseDto)
                .members(projectMemberDetailResponseDtos)
                .works(workProjectDetailResponseDto)
                .status(project.getStatus())
                .crewNumber(project.getCrewNumber())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .createDate(project.getCreateDate())
                .updateDate(project.getUpdateDate())
                .build();
    }
}
