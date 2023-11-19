package com.example.demo.controller;

import com.example.demo.dto.Common.ResponseDto;
import com.example.demo.dto.Milestone.Request.MilestoneCreateRequestDto;
import com.example.demo.dto.Milestone.Response.MilestoneCreateResponseDto;
import com.example.demo.repository.MileStoneRepository;
import com.example.demo.service.MilestoneService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MileStoneController {

    private final MilestoneService milestoneService;

    @PostMapping("/api/project/{projectId}/milestone")
    public ResponseEntity<ResponseDto<?>> create(@PathVariable("projectId") Long projectId, @RequestBody MilestoneCreateRequestDto milestoneCreateRequestDto){
        MilestoneCreateResponseDto result = milestoneService.create(projectId, milestoneCreateRequestDto);
        return new ResponseEntity<>(new ResponseDto<>("success", result), HttpStatus.OK);
    }
}
