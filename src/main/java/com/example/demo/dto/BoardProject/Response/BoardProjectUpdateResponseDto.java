package com.example.demo.dto.BoardProject.Response;

import com.example.demo.dto.Board.Response.BoardUpdateResponseDto;
import com.example.demo.dto.Project.Response.ProjectUpdateResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardProjectUpdateResponseDto {
    private BoardUpdateResponseDto board;
    private ProjectUpdateResponseDto project;
}
