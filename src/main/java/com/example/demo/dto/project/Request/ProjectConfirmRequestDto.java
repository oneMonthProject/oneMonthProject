package com.example.demo.dto.Project.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProjectConfirmRequestDto {
    private Long positionId;
    private Long projectMemberAuthId;
}
