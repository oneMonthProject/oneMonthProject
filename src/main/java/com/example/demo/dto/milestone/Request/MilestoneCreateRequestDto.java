package com.example.demo.dto.milestone.Request;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MilestoneCreateRequestDto {
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
