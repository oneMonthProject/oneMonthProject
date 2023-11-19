package com.example.demo.service;

import com.example.demo.dto.Work.Request.WorkCreateRequestDto;
import com.example.demo.dto.Work.Request.WorkUpdateCompleteStatusRequestDto;
import com.example.demo.dto.Work.Request.WorkUpdateContentRequestDto;
import com.example.demo.dto.Work.Request.WorkUpdateRequestDto;
import com.example.demo.dto.Work.Response.WorkReadResponseDto;
import com.example.demo.global.exception.customexception.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class WorkService {
    private final WorkRepository workRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final MileStoneRepository mileStoneRepository;
    private final ProjectMemberRepository projectMemberRepository;
    /**
     * 프로젝트 내 업무 생성
     * TODO : 유저 jwt token으로 받아와서 넣어주기
     * @param projectId
     * @param milestoneId
     */
    public void create(Long projectId, Long milestoneId, WorkCreateRequestDto workCreateRequestDto){
        Project project = projectRepository.findById(projectId).orElseThrow(() -> ProjectCustomException.NOT_FOUND_PROJECT);
        Milestone milestone = mileStoneRepository.findById(milestoneId).orElseThrow(() -> MilestoneCustomException.NOT_FOUND_MILESTONE);
        User user = userRepository.findById(workCreateRequestDto.getAssignedUserId()).orElseThrow(() -> UserCustomException.NOT_FOUND_USER);
        ProjectMember projectMember = projectMemberRepository.findProjectMemberByProjectAndUser(project, user).orElseThrow(() -> ProjectMemberCustomException.NOT_FOUND_PROJECT_MEMBER);


        Work work = Work.builder()
                .project(project)
                .milestone(milestone)
                .assignedUserId(user)
                .lastModifiedMember(projectMember)
                .content(workCreateRequestDto.getContent())
                .expireStatus(false)
                .completeStatus(false)
                .startDate(workCreateRequestDto.getStartDate())
                .endDate(workCreateRequestDto.getEndDate())
                .build();
        workRepository.save(work);
    }

    @Transactional(readOnly = true)
    public List<WorkReadResponseDto> getAllByMilestone(Long projectId, Long milestoneId){
        Project project = projectRepository.findById(projectId).orElseThrow(() -> ProjectCustomException.NOT_FOUND_PROJECT);
        Milestone milestone = mileStoneRepository.findById(milestoneId).orElseThrow(() -> MilestoneCustomException.NOT_FOUND_MILESTONE);
        List<Work> works = workRepository.findWorksByProjectAndMilestone(project, milestone).orElseThrow(() -> WorkCustomException.NOT_FOUND_WORK);
        List<WorkReadResponseDto> workReadResponseDtos = new ArrayList<>();
        for (Work work : works) {
            WorkReadResponseDto workReadResponseDto = WorkReadResponseDto.of(work);
            workReadResponseDtos.add(workReadResponseDto);
        }

        return workReadResponseDtos;
    }

    public WorkReadResponseDto getOne(Long workId){
        Work work = workRepository.findById(workId).orElseThrow(() -> WorkCustomException.NOT_FOUND_WORK);
        return WorkReadResponseDto.of(work);
    }

    /**
     * 업무 수정
     * TODO : 마지막 수정자 현재 유저인 구성원으로 변경
     * @param workId
     */
    
    public void update(Long workId, WorkUpdateRequestDto workUpdateRequestDto){
        Work work = workRepository.findById(workId).orElseThrow(() -> WorkCustomException.NOT_FOUND_WORK);
        User user = userRepository.findById(1L).orElseThrow(() -> UserCustomException.NOT_FOUND_USER);
        ProjectMember projectMember = projectMemberRepository.findProjectMemberByProjectAndUser(work.getProject(), user).orElseThrow(() -> ProjectMemberCustomException.NOT_FOUND_PROJECT_MEMBER);

        work = Work.builder()
                .project(work.getProject())
                .milestone(work.getMilestone())
                .assignedUserId(work.getAssignedUserId())
                .lastModifiedMember(projectMember)
                .content(workUpdateRequestDto.getContent())
                .expireStatus(work.isExpireStatus())
                .completeStatus(work.isCompleteStatus())
                .startDate(workUpdateRequestDto.getStartDate())
                .endDate(workUpdateRequestDto.getEndDate())
                .build();

        workRepository.save(work);
    }

    public void delete(Long workId){
        Work work = workRepository.findById(workId).orElseThrow(() -> WorkCustomException.NOT_FOUND_WORK);
        workRepository.delete(work);
    }

    /**
     * 업무 내용 수정
     * TODO : 마지막 변경자 바꿔줘야 함.
     * @param workId
     * @param workUpdateContentRequestDto
     */
    public void updateContent(Long workId, WorkUpdateContentRequestDto workUpdateContentRequestDto){
        Work work = workRepository.findById(workId).orElseThrow(() -> WorkCustomException.NOT_FOUND_WORK);
        User user = userRepository.findById(1L).orElseThrow(() -> UserCustomException.NOT_FOUND_USER);
        ProjectMember projectMember = projectMemberRepository.findProjectMemberByProjectAndUser(work.getProject(), user).orElseThrow(() -> ProjectMemberCustomException.NOT_FOUND_PROJECT_MEMBER);

        work = Work.builder()
                .project(work.getProject())
                .milestone(work.getMilestone())
                .assignedUserId(work.getAssignedUserId())
                .lastModifiedMember(projectMember)
                .content(workUpdateContentRequestDto.getContent())
                .expireStatus(work.isExpireStatus())
                .completeStatus(work.isCompleteStatus())
                .startDate(work.getStartDate())
                .endDate(work.getEndDate())
                .build();

        workRepository.save(work);
    }

    /**
     * 업무 완료 여부 수정
     * TODO : 마지막 변경자 바꿔줘야 함.
     * @param workId
     * @param workUpdateCompleteStatusRequestDto
     */

    public void updateCompleteStatus(Long workId, WorkUpdateCompleteStatusRequestDto workUpdateCompleteStatusRequestDto) {
        Work work = workRepository.findById(workId).orElseThrow(() -> WorkCustomException.NOT_FOUND_WORK);
        User user = userRepository.findById(1L).orElseThrow(() -> UserCustomException.NOT_FOUND_USER);
        ProjectMember projectMember = projectMemberRepository.findProjectMemberByProjectAndUser(work.getProject(), user).orElseThrow(() -> ProjectMemberCustomException.NOT_FOUND_PROJECT_MEMBER);

        work = Work.builder()
                .project(work.getProject())
                .milestone(work.getMilestone())
                .assignedUserId(work.getAssignedUserId())
                .lastModifiedMember(projectMember)
                .content(work.getContent())
                .expireStatus(work.isExpireStatus())
                .completeStatus(workUpdateCompleteStatusRequestDto.getCompleteStatus())
                .startDate(work.getStartDate())
                .endDate(work.getEndDate())
                .build();

        workRepository.save(work);
    }
}
