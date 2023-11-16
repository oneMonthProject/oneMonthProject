package com.example.demo.controller;

import com.example.demo.dto.Board.Request.BoardSearchRequestDto;
import com.example.demo.dto.Board.Response.BoardSearchResponseDto;
import com.example.demo.dto.BoardProject.Request.BoardProjectCreateRequestDto;
import com.example.demo.dto.BoardProject.Request.BoardProjectUpdateRequestDto;
import com.example.demo.dto.BoardProject.Response.BoardProjectCreateResponseDto;
import com.example.demo.dto.BoardProject.Response.BoardProjectUpdateResponseDto;
import com.example.demo.dto.Common.ResponseDto;
import com.example.demo.service.BoardService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/board")
public class BoardController {

    @Autowired BoardService boardService;

    @GetMapping("")
    public ResponseEntity<ResponseDto<?>> get(@RequestBody BoardSearchRequestDto dto) {
        try {
            List<BoardSearchResponseDto> result = boardService.search(dto);
            return new ResponseEntity<>(new ResponseDto<>("success", result), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(
                    new ResponseDto<>("fail", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto<?>> create(
            @RequestBody BoardProjectCreateRequestDto requestDto) {
        try {
            BoardProjectCreateResponseDto result = boardService.create(requestDto);
            return new ResponseEntity<>(new ResponseDto<>("success", result), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(
                    new ResponseDto<>("fail", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("")
    public ResponseEntity<ResponseDto<?>> update(BoardProjectUpdateRequestDto requestDto) {
        try {
            BoardProjectUpdateResponseDto result = boardService.update(requestDto);
            return new ResponseEntity<>(new ResponseDto<>("success", result), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(
                    new ResponseDto<>("fail", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<ResponseDto<?>> delete(@PathVariable("boardId") Long boardId) {
        try {
            boardService.delete(boardId);
            return new ResponseEntity<>(new ResponseDto<>("success", null), HttpStatus.NO_CONTENT);
        } catch (Exception exception) {
            return new ResponseEntity<>(
                    new ResponseDto<>("fail", exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
