package com.example.demo.controller;

import com.example.demo.dto.common.ResponseDto;
import com.example.demo.dto.project.Request.ProjectConfirmRequestDto;
import com.example.demo.dto.project.Request.ProjectParticipateRequestDto;
import com.example.demo.dto.project.Response.ProjectMeResponseDto;
import com.example.demo.dto.project.Response.ProjectSpecificDetailResponseDto;
import com.example.demo.service.ProjectService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
@AllArgsConstructor
public class ProjectController {

    public final ProjectService projectService;

    @GetMapping("/me")
    public ResponseEntity<ResponseDto<?>> getMyProjects() {
        List<ProjectMeResponseDto> result = projectService.getMyProjects();
        return new ResponseEntity<>(new ResponseDto<>("success", result), HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ResponseDto<?>> getDetail(@PathVariable("projectId") Long projectId) {
        ProjectSpecificDetailResponseDto result = projectService.getDetail(projectId);
        return new ResponseEntity<>(new ResponseDto<>("success", result), HttpStatus.OK);
    }

    @PostMapping("/{projectId}/participate")
    public ResponseEntity<ResponseDto<?>> participate(
            @PathVariable("projectId") Long projectId,
            @RequestBody @Valid ProjectParticipateRequestDto projectParticipateRequestDto) {
        projectService.sendParticipateAlert(projectId, projectParticipateRequestDto);
        return new ResponseEntity<>(new ResponseDto<>("success", null), HttpStatus.OK);
    }

    @PostMapping("/{projectId}/participate/confirm")
    public ResponseEntity<ResponseDto<?>> confirm(
            @PathVariable("projectId") Long projectId,
            @RequestBody @Valid ProjectConfirmRequestDto projectConfirmRequestDto) {
        projectService.confirm(projectId, projectConfirmRequestDto);
        return new ResponseEntity<>(new ResponseDto<>("success", null), HttpStatus.OK);
    }

    @PostMapping("/{projectId}/end")
    public ResponseEntity<ResponseDto<?>> end(@PathVariable("projectId") Long projectId) {
        projectService.end(projectId);
        return new ResponseEntity<>(new ResponseDto<>("success", null), HttpStatus.OK);
    }
}
