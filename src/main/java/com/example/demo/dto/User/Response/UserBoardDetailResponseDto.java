package com.example.demo.dto.User.Response;

import com.example.demo.model.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserBoardDetailResponseDto {
    private Long userId;
    private String nickName;
    private String userProfileImgSrc;
    public static UserBoardDetailResponseDto of(User user) {
        return UserBoardDetailResponseDto.builder()
                .userId(user.getId())
                .nickName(user.getNickname())
                .userProfileImgSrc(user.getProfileImgSrc())
                .build();
    }
}
