package com.example.demo.dto.work.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class WorkUpdateRequestDto {
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
