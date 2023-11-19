package com.example.demo.controller;

import com.example.demo.dto.Board.Request.BoardSearchRequestDto;
import com.example.demo.dto.Board.Response.BoardDetailResponseDto;
import com.example.demo.dto.Board.Response.BoardSearchResponseDto;
import com.example.demo.dto.Board.Response.BoardTotalDetailResponseDto;
import com.example.demo.dto.BoardProject.Request.BoardProjectCreateRequestDto;
import com.example.demo.dto.BoardProject.Request.BoardProjectUpdateRequestDto;
import com.example.demo.dto.BoardProject.Response.BoardProjectCreateResponseDto;
import com.example.demo.dto.BoardProject.Response.BoardProjectUpdateResponseDto;
import com.example.demo.dto.Common.ResponseDto;
import com.example.demo.dto.Project.Response.ProjectDetailResponseDto;
import com.example.demo.service.BoardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

private final BoardService boardService;

    @GetMapping("/search")
    public ResponseEntity<ResponseDto<?>> get(@RequestBody BoardSearchRequestDto dto) {
        List<BoardSearchResponseDto> result = boardService.search(dto);
        return new ResponseEntity<>(new ResponseDto<>("success", result), HttpStatus.OK);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<ResponseDto<?>> getDetail(@PathVariable("boardId") Long boardId){
        BoardTotalDetailResponseDto result = boardService.getDetail(boardId);
        return new ResponseEntity<>(new ResponseDto<>("success", result), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto<?>> create(@RequestBody BoardProjectCreateRequestDto requestDto) {
        BoardProjectCreateResponseDto result = boardService.create(requestDto);
        return new ResponseEntity<>(new ResponseDto<>("success", result), HttpStatus.OK);
    }

    @PatchMapping("/{boardId}")
    public ResponseEntity<ResponseDto<?>> update(@PathVariable("boardId") Long boardId, BoardProjectUpdateRequestDto requestDto) {
        BoardProjectUpdateResponseDto result = boardService.update(boardId, requestDto);
        return new ResponseEntity<>(new ResponseDto<>("success", result), HttpStatus.OK);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<ResponseDto<?>> delete(@PathVariable("boardId") Long boardId) {
        boardService.delete(boardId);
        return new ResponseEntity<>(new ResponseDto<>("success", null), HttpStatus.NO_CONTENT);
    }
}
