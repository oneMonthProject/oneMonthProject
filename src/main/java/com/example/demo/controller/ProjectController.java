package com.example.demo.controller;

import com.example.demo.dto.Common.ResponseDto;
import com.example.demo.dto.Project.Response.ProjectDetailResponseDto;
import com.example.demo.dto.Project.Response.ProjectMeResponseDto;
import com.example.demo.dto.Project.Response.ProjectSpecificDetailResponseDto;
import com.example.demo.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/project")
@AllArgsConstructor
public class ProjectController {

    public final ProjectService projectService;


    @GetMapping("/me")
    public ResponseEntity<ResponseDto<?>> getMyProjects(){
        List<ProjectMeResponseDto> result = projectService.getMyProjects();
        return new ResponseEntity<>(new ResponseDto<>("success", result), HttpStatus.OK);
    }

    @GetMapping("{projectId}")
    public ResponseEntity<ResponseDto<?>> getDetail(@PathVariable("projectId") Long projectId){
        ProjectSpecificDetailResponseDto result = projectService.getDetail(projectId);
        return new ResponseEntity<>(new ResponseDto<>("success", result), HttpStatus.OK);
    }
}
