package com.example.demo.dto.User.Response;

import com.example.demo.model.Project;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserProjectResponseDto {
    private Long userId;
    private String email;
    private String nickName;
    private String profileImgSrc;

    public static UserProjectResponseDto of(Project project) {
        return UserProjectResponseDto.builder()
                .userId(project.getUser().getId())
                .email(project.getUser().getEmail())
                .nickName(project.getUser().getNickname())
                .profileImgSrc(project.getUser().getProfileImgSrc())
                .build();
    }
}
