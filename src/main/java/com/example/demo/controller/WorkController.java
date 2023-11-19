package com.example.demo.controller;

import com.example.demo.dto.Common.ResponseDto;
import com.example.demo.dto.Work.Request.WorkCreateRequestDto;
import com.example.demo.dto.Work.Request.WorkUpdateCompleteStatusRequestDto;
import com.example.demo.dto.Work.Request.WorkUpdateContentRequestDto;
import com.example.demo.dto.Work.Request.WorkUpdateRequestDto;
import com.example.demo.dto.Work.Response.WorkReadResponseDto;
import com.example.demo.service.WorkService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class WorkController {

    private final WorkService workService;

    @PostMapping("/api/project/{projectId}/milestone/{milestoneId}/work")
    public ResponseEntity<ResponseDto<?>> create(@PathVariable("projectId") Long projectId, @PathVariable("milestoneId") Long milestoneId, WorkCreateRequestDto workCreateRequestDto){
        workService.create(projectId, milestoneId, workCreateRequestDto);
        return new ResponseEntity<>(new ResponseDto<>("success", null), HttpStatus.OK);
    }

    @GetMapping("/api/project/{projectId}/milestone/{milestoneId}/work")
    public ResponseEntity<ResponseDto<?>> getAllByMilestone(@PathVariable("projectId") Long projectId, @PathVariable("milestoneId") Long milestoneId){
        List<WorkReadResponseDto> result = workService.getAllByMilestone(projectId, milestoneId);
        return new ResponseEntity<>(new ResponseDto<>("success", result), HttpStatus.OK);
    }

    @GetMapping("/api/work/{workId}")
    public ResponseEntity<ResponseDto<?>> getOne(@PathVariable("workId") Long workId){
        WorkReadResponseDto result = workService.getOne(workId);
        return new ResponseEntity<>(new ResponseDto<>("success", result), HttpStatus.OK);
    }

    @PatchMapping("/api/work/{workId}")
    public ResponseEntity<ResponseDto<?>> update(@PathVariable("workId") Long workId, WorkUpdateRequestDto workUpdateRequestDto){
        workService.update(workId, workUpdateRequestDto);
        return new ResponseEntity<>(new ResponseDto<>("success", null), HttpStatus.OK);
    }

    @DeleteMapping("/api/work/{workId}")
    public ResponseEntity<ResponseDto<?>> update(@PathVariable("workId") Long workId){
        workService.delete(workId);
        return new ResponseEntity<>(new ResponseDto<>("success", null), HttpStatus.OK);
    }

    @PatchMapping("/api/work/{workId}/content")
    public ResponseEntity<ResponseDto<?>> updateContent(@PathVariable("workId") Long workId, WorkUpdateContentRequestDto workUpdateContentRequestDto){
        workService.updateContent(workId, workUpdateContentRequestDto);
        return new ResponseEntity<>(new ResponseDto<>("success", null), HttpStatus.OK);
    }

    @PatchMapping("/api/work/{workId}/completeStatus")
    public ResponseEntity<ResponseDto<?>> updateCompleteStatus(@PathVariable("workId") Long workId,  WorkUpdateCompleteStatusRequestDto workUpdateCompleteStatusRequestDto){
        workService.updateCompleteStatus(workId, workUpdateCompleteStatusRequestDto);
        return new ResponseEntity<>(new ResponseDto<>("success", null), HttpStatus.OK);
    }
}
