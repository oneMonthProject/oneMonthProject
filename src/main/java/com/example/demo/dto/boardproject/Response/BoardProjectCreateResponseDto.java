package com.example.demo.dto.boardproject.Response;

import com.example.demo.dto.board.Response.BoardCreateResponseDto;
import com.example.demo.dto.project.Response.ProjectCreateResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardProjectCreateResponseDto {
    private BoardCreateResponseDto board;
    private ProjectCreateResponseDto project;
}
