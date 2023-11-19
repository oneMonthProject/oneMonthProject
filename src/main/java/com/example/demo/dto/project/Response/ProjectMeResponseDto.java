package com.example.demo.dto.Project.Response;

import com.example.demo.constant.ProjectStatus;
import com.example.demo.dto.ProjectMember.Response.MyProjectMemberResponseDto;
import com.example.demo.dto.TrustGrade.TrustGradeDto;
import com.example.demo.model.Project;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ProjectMeResponseDto {
    private Long projectId;
    private String name;
    private String subject;
    private TrustGradeDto trustGrade;
    private List<MyProjectMemberResponseDto> members;
    private ProjectStatus status;
    private int crewNumber;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public static ProjectMeResponseDto of(Project project,
                                          TrustGradeDto trustGradeDto,
                                          List<MyProjectMemberResponseDto> myProjectMemberResponseDtos
    ) {
        return ProjectMeResponseDto.builder()
                .projectId(project.getId())
                .name(project.getName())
                .subject(project.getSubject())
                .trustGrade(trustGradeDto)
                .members(myProjectMemberResponseDtos)
                .status(project.getStatus())
                .crewNumber(project.getCrewNumber())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .createDate(project.getCreateDate())
                .updateDate(project.getUpdateDate())
                .build();
    }

}
