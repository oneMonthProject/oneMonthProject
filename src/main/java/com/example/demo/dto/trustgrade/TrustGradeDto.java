package com.example.demo.dto.trustgrade;

import com.example.demo.model.TrustGrade;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TrustGradeDto {
    private String name;
    private int score;

    public static TrustGradeDto of(TrustGrade trustGrade) {
        return TrustGradeDto.builder()
                .name(trustGrade.getName())
                .score(trustGrade.getScore())
                .build();
    }
}
