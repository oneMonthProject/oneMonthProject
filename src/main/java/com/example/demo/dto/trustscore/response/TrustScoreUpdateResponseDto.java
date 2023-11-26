package com.example.demo.dto.trustscore.response;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
@Getter
@Builder
public class TrustScoreUpdateResponseDto {
    @NotNull(message = "사용자값은 필수입니다.")
    private Long userId;
    private int score;
}
