package com.example.demo.dto.boardproject.Response;

import com.example.demo.dto.board.Response.BoardUpdateResponseDto;
import com.example.demo.dto.project.Response.ProjectUpdateResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardProjectUpdateResponseDto {
    private BoardUpdateResponseDto board;
    private ProjectUpdateResponseDto project;
}
