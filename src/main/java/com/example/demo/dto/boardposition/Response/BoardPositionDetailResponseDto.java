package com.example.demo.dto.boardposition.Response;

import com.example.demo.dto.position.Response.PositionResponseDto;
import com.example.demo.model.BoardPosition;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardPositionDetailResponseDto {
    private Long boardPositionId;
    private PositionResponseDto position;

    public static BoardPositionDetailResponseDto of(BoardPosition boardPosition, PositionResponseDto positionResponseDto) {
        return BoardPositionDetailResponseDto.builder()
                .boardPositionId(boardPosition.getId())
                .position(positionResponseDto)
                .build();
    }
}
