package com.example.demo.dto.ProjectMember.Response;

import com.example.demo.dto.User.Response.UserMyProjectResponseDto;
import com.example.demo.model.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyProjectMemberResponseDto {
    private Long projectMemberId;
    private UserMyProjectResponseDto user;

    public static MyProjectMemberResponseDto of(
            ProjectMember projectMember, UserMyProjectResponseDto userMyProjectResponseDto) {
        return MyProjectMemberResponseDto.builder()
                .projectMemberId(projectMember.getId())
                .user(userMyProjectResponseDto)
                .build();
    }
}
