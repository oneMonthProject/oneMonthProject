package com.example.demo.dto.User.Response;

import com.example.demo.model.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserProjectDetailResponseDto {

    private Long userId;
    private String nickname;
    private String profileImgSrc;

    public static UserProjectDetailResponseDto of(User user) {
        return UserProjectDetailResponseDto.builder()
                .userId(user.getId())
                .nickname(user.getNickname())
                .profileImgSrc(user.getProfileImgSrc())
                .build();
    }
}
