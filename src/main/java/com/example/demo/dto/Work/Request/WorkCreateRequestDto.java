package com.example.demo.dto.Work.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class WorkCreateRequestDto {
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long assignedUserId;

}
