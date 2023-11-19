package com.example.demo.dto.project.Response;

import com.example.demo.dto.trustgrade.TrustGradeDto;
import com.example.demo.model.Project;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProjectSearchResponseDto {
    private Long projectId;
    private String name;
    private String subject;
    private TrustGradeDto trustGrade;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public static ProjectSearchResponseDto of(Project project) {
        TrustGradeDto trustGradeDto = TrustGradeDto.of(project.getTrustGrade());

        return ProjectSearchResponseDto.builder()
                .projectId(project.getId())
                .name(project.getName())
                .subject(project.getSubject())
                .trustGrade(trustGradeDto)
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .build();
    }
}
