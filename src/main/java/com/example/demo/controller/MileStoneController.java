package com.example.demo.controller;

import com.example.demo.dto.Common.ResponseDto;
import com.example.demo.dto.Milestone.Request.MileStoneUpdateRequestDto;
import com.example.demo.dto.Milestone.Request.MilestoneCreateRequestDto;
import com.example.demo.dto.Milestone.Request.MilestoneUpdateContentRequestDto;
import com.example.demo.dto.Milestone.Request.MilestoneUpdateDateRequestDto;
import com.example.demo.dto.Milestone.Response.MilestoneCreateResponseDto;
import com.example.demo.dto.Milestone.Response.MilestoneReadResponseDto;
import com.example.demo.service.MilestoneService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class MileStoneController {

    private final MilestoneService milestoneService;

    @PostMapping("/api/project/{projectId}/milestone")
    public ResponseEntity<ResponseDto<?>> create(@PathVariable("projectId") Long projectId, @RequestBody MilestoneCreateRequestDto milestoneCreateRequestDto){
        MilestoneCreateResponseDto result = milestoneService.create(projectId, milestoneCreateRequestDto);
        return new ResponseEntity<>(new ResponseDto<>("success", result), HttpStatus.OK);
    }

    @GetMapping("/api/project/{projectId}/milestone")
    public ResponseEntity<ResponseDto<?>> getAll(@PathVariable("projectId") Long projectId){
        List<MilestoneReadResponseDto> result = milestoneService.getAll(projectId);
        return new ResponseEntity<>(new ResponseDto<>("success", result), HttpStatus.OK);
    }

    @GetMapping("/api/milestone/{milestoneId}")
    public ResponseEntity<ResponseDto<?>> getOne(@PathVariable("milestoneId") Long mileStoneId){
        MilestoneReadResponseDto result = milestoneService.getOne(mileStoneId);
        return new ResponseEntity<>(new ResponseDto<>("success", result), HttpStatus.OK);
    }

    @PatchMapping("/api/milestone/{milestoneId}")
    public ResponseEntity<ResponseDto<?>> update(@PathVariable("milestoneId") Long mileStoneId, MileStoneUpdateRequestDto mileStoneUpdateRequestDto){
        milestoneService.update(mileStoneId, mileStoneUpdateRequestDto);
        return new ResponseEntity<>(new ResponseDto<>("success", null), HttpStatus.OK);
    }

    @DeleteMapping("/api/milestone/{milestoneId}")
    public ResponseEntity<ResponseDto<?>> delete(@PathVariable("milestoneId") Long mileStoneId){
        milestoneService.delete(mileStoneId);
        return new ResponseEntity<>(new ResponseDto<>("success", null), HttpStatus.OK);
    }

    @PatchMapping("/api/milestone/{milestoneId}")
    public ResponseEntity<ResponseDto<?>> updateContent(@PathVariable("milestoneId") Long mileStoneId, MilestoneUpdateContentRequestDto milestoneUpdateContentRequestDto){
        milestoneService.updateContent(mileStoneId, milestoneUpdateContentRequestDto);
        return new ResponseEntity<>(new ResponseDto<>("success", null), HttpStatus.OK);
    }

    @PatchMapping("/api/milestone/{milestoneId}")
    public ResponseEntity<ResponseDto<?>> updateDate(@PathVariable("milestoneId") Long mileStoneId, MilestoneUpdateDateRequestDto milestoneUpdateDateRequestDto ){
        milestoneService.updateDate(mileStoneId, milestoneUpdateDateRequestDto);
        return new ResponseEntity<>(new ResponseDto<>("success", null), HttpStatus.OK);
    }
}
