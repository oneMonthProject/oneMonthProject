package com.example.demo.dto.work.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WorkUpdateAssignUserRequestDto {
    private Long assignedUserId;
}