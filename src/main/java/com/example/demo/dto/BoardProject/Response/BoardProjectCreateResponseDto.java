package com.example.demo.dto.BoardProject.Response;

import com.example.demo.dto.Board.Response.BoardCreateResponseDto;
import com.example.demo.dto.Project.Response.ProjectCreateResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardProjectCreateResponseDto {
    private BoardCreateResponseDto board;
    private ProjectCreateResponseDto project;
}
