package com.example.demo.dto.Board.Response;

import com.example.demo.dto.Project.Response.ProjectSearchResponseDto;
import com.example.demo.dto.User.Response.UserSearchResponseDto;
import com.example.demo.model.Board;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardSearchResponseDto {
    private Long boardId;
    private String boardTitle;
    private String boardContent;
    private ProjectSearchResponseDto project;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int boardPageView;
    private boolean boardCompleteStatus;
    private UserSearchResponseDto user;
    private String boardContact;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public static BoardSearchResponseDto of(Board board) {
        ProjectSearchResponseDto boardProjectSearchResponseDto =
                ProjectSearchResponseDto.of(board.getProject());
        UserSearchResponseDto userSearchResponseDto = UserSearchResponseDto.of(board.getUser());

        return BoardSearchResponseDto.builder()
                .boardId(board.getId())
                .boardTitle(board.getTitle())
                .boardContent(board.getContent())
                .project(boardProjectSearchResponseDto)
                .boardPageView(board.getPageView())
                .boardCompleteStatus(board.isCompleteStatus())
                .user(userSearchResponseDto)
                .boardContent(board.getContent())
                .createDate(board.getCreateDate())
                .updateDate(board.getUpdateDate())
                .build();
    }
}
