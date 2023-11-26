package com.example.demo.dto.trustscorehistory;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProjectUserHistoryDto {
    Long workId;
    Integer score;
}
