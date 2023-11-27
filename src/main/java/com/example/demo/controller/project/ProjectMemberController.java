package com.example.demo.controller.project;

import com.example.demo.dto.common.ResponseDto;
import com.example.demo.dto.projectmember.response.ProjectMemberReadCrewDetailResponseDto;
import com.example.demo.service.project.ProjectMemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projectmember")
@AllArgsConstructor
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;

    @PostMapping("/{projectMemberId}/withdrawl")
    public ResponseEntity<ResponseDto<?>> withdrawl(@PathVariable("projectMemberId") Long projectMemberId) {
        projectMemberService.sendWithdrawlAlert(projectMemberId);
        return new ResponseEntity<>(ResponseDto.success("success"), HttpStatus.OK);
    }

    @PostMapping("/{projectMemberId}/withdrawl/confirm")
    public ResponseEntity<ResponseDto<?>> withdrawlConfirm(
            @PathVariable("projectMemberId") Long projectMemberId) {
        projectMemberService.withdrawlConfirm(projectMemberId);
        return new ResponseEntity<>(ResponseDto.success("success"), HttpStatus.OK);
    }

    @PostMapping("/{projectMemberId}/withdrawl/force")
    public ResponseEntity<ResponseDto<?>> withdrawlForce(@PathVariable("projectMemberId") Long projectMemberId) {
        projectMemberService.withdrawlForce(projectMemberId);
        return new ResponseEntity<>(ResponseDto.success("success"), HttpStatus.OK);
    }

    @GetMapping("/{projectMemberId}")
    public ResponseEntity<ResponseDto<?>> getDetail(@PathVariable("projectMemberId") Long projectMemberId) {
        ProjectMemberReadCrewDetailResponseDto result = projectMemberService.getCrewDetail(projectMemberId);
        return new ResponseEntity<>(ResponseDto.success("success", result), HttpStatus.OK);
    }
}
