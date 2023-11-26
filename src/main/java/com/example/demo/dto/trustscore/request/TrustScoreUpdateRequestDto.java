package com.example.demo.dto.trustscore.request;

import lombok.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class TrustScoreUpdateRequestDto {
    @NotNull(message = "사용자값은 필수입니다.")
    private Long userId;
    private Long projectId;
    private Long milestoneId;
    private Long workId;
    @NotNull(message = "점수타입값은 필수입니다.")
    private Long scoreTypeId;
}
