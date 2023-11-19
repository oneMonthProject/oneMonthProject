package com.example.demo.dto.milestone.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MilestoneUpdateDateRequestDto {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
