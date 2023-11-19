package com.example.demo.service;

import com.example.demo.dto.Milestone.Request.MileStoneUpdateRequestDto;
import com.example.demo.dto.Milestone.Request.MilestoneCreateRequestDto;
import com.example.demo.dto.Milestone.Response.MilestoneCreateResponseDto;
import com.example.demo.dto.Milestone.Response.MilestoneReadResponseDto;
import com.example.demo.global.exception.customexception.MilestoneCustomException;
import com.example.demo.global.exception.customexception.ProjectCustomException;
import com.example.demo.model.Milestone;
import com.example.demo.model.Project;
import com.example.demo.repository.MileStoneRepository;
import com.example.demo.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class MilestoneService {
    private final MileStoneRepository mileStoneRepository;
    private final ProjectRepository projectRepository;

    /**
     * 마일스톤 생성
     * TODO : 매니저만 가능하도록 USER 가져와서 비교해봐야함.
     * @param projectId
     * @param milestoneCreateRequestDto
     * @return
     */

    public MilestoneCreateResponseDto create(Long projectId, MilestoneCreateRequestDto milestoneCreateRequestDto){
        Project project = projectRepository.findById(projectId).orElseThrow(() -> ProjectCustomException.NOT_FOUND_PROJECT);

        Milestone mileStone = Milestone.builder()
                .project(project)
                .content(milestoneCreateRequestDto.getName())
                .startDate(milestoneCreateRequestDto.getStartDate())
                .endDate(milestoneCreateRequestDto.getEndDate())
                .expireStatus(false)
                .completeStatus(false)
                .build();

        Milestone savedMilestone = mileStoneRepository.save(mileStone);

        return MilestoneCreateResponseDto.of(savedMilestone);
    }

    @Transactional(readOnly = true)
    public List<MilestoneReadResponseDto> getAll(Long projectId){
        Project project = projectRepository.findById(projectId).orElseThrow(() -> ProjectCustomException.NOT_FOUND_PROJECT);
        List<Milestone> milestonesByProject = mileStoneRepository.findMilestonesByProject(project).orElseThrow(() -> MilestoneCustomException.NOT_FOUND_MILESTONE);
        List<MilestoneReadResponseDto> milestoneReadResponseDtos = new ArrayList<>();
        for (Milestone milestone : milestonesByProject) {
            MilestoneReadResponseDto milestoneReadResponseDto = MilestoneReadResponseDto.of(milestone);
            milestoneReadResponseDtos.add(milestoneReadResponseDto);
        }

        return milestoneReadResponseDtos;
    }

    @Transactional(readOnly = true)
    public MilestoneReadResponseDto getOne(Long milestoneId){
        Milestone milestone = mileStoneRepository.findById(milestoneId).orElseThrow(() -> MilestoneCustomException.NOT_FOUND_MILESTONE);

        return MilestoneReadResponseDto.of(milestone);
    }

    /**
     * 프로젝트 내 마일스톤 수정(*매니저만 가능)
     * TODO : 매니저만 수정 가능하도록 변경
     * @param milestoneId
     * @param mileStoneUpdateRequestDto
     */
    public void update(Long milestoneId, MileStoneUpdateRequestDto mileStoneUpdateRequestDto){
        Milestone milestone = mileStoneRepository.findById(milestoneId).orElseThrow(() -> MilestoneCustomException.NOT_FOUND_MILESTONE);
        milestone = Milestone.builder()
                .project(milestone.getProject())
                .content(mileStoneUpdateRequestDto.getContent())
                .startDate(mileStoneUpdateRequestDto.getStartDate())
                .endDate(mileStoneUpdateRequestDto.getEndDate())
                .expireStatus(milestone.isExpireStatus())
                .completeStatus(milestone.isCompleteStatus())
                .build();

        mileStoneRepository.save(milestone);
    }
}
