package com.example.demo.dto.board.Response;

import com.example.demo.dto.project.Response.ProjectDetailResponseDto;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class BoardTotalDetailResponseDto {
    private BoardDetailResponseDto board;
    private ProjectDetailResponseDto project;

    public static BoardTotalDetailResponseDto of(
            BoardDetailResponseDto boardDetailResponseDto,
            ProjectDetailResponseDto projectDetailResponseDto
    ) {
        return BoardTotalDetailResponseDto.builder()
                .board(boardDetailResponseDto)
                .project(projectDetailResponseDto)
                .build();
    }
}
