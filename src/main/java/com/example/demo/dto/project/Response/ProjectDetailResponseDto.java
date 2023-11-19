package com.example.demo.dto.project.Response;

import com.example.demo.constant.ProjectStatus;
import com.example.demo.dto.trustgrade.TrustGradeDto;
import com.example.demo.dto.User.Response.UserProjectResponseDto;
import com.example.demo.model.Project;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ProjectDetailResponseDto {
    private Long projectId;
    private String name;
    private String subject;
    private TrustGradeDto trustGrade;
    private UserProjectResponseDto user;
    private ProjectStatus status;
    private int crewNumber;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public static ProjectDetailResponseDto of(Project project, TrustGradeDto trustGradeDto, UserProjectResponseDto userProjectResponseDto) {
        return ProjectDetailResponseDto.builder()
                .projectId(project.getId())
                .name(project.getName())
                .subject(project.getSubject())
                .trustGrade(trustGradeDto)
                .user(userProjectResponseDto)
                .status(project.getStatus())
                .crewNumber(project.getCrewNumber())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .createDate(project.getCreateDate())
                .updateDate(project.getUpdateDate())
                .build();
    }
}
