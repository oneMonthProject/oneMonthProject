package com.example.demo.dto.User;

import com.example.demo.dto.TrustGrade.TrustGradeDto;
import com.example.demo.model.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSearchResponseDto {
    private String email;
    private String nickname;
    private String profileImgSrc;
    private TrustGradeDto trustGrade;

    public static UserSearchResponseDto of(User user) {
        TrustGradeDto UserTrustGradeDto = TrustGradeDto.of(user.getTrustGrade());

        return UserSearchResponseDto.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .profileImgSrc(user.getProfileImgSrc())
                .trustGrade(UserTrustGradeDto)
                .build();
    }
}
