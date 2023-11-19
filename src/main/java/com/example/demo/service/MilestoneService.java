package com.example.demo.service;

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

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
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
}
