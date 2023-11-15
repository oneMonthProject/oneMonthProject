package com.example.demo.dto.BoardProject.Request;

import com.example.demo.dto.Board.Request.BoardCreateRequestDto;
import com.example.demo.dto.project.ProjectCreateRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class BoardProjectCreateRequestDto {
    private BoardCreateRequestDto board;
    private ProjectCreateRequestDto project;
}