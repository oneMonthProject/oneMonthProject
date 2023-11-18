package com.example.demo.dto.BoardPosition.Response;

import com.example.demo.dto.Position.Response.PositionResponseDto;
import com.example.demo.model.Board;
import com.example.demo.model.BoardPosition;
import com.example.demo.model.Position;
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
