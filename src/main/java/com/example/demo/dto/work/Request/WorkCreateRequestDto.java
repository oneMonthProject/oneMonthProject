package com.example.demo.dto.work.Request;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WorkCreateRequestDto {
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long assignedUserId;
}
