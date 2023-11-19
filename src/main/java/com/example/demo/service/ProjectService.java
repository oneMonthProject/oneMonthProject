package com.example.demo.service;

import com.example.demo.constant.AlertType;
import com.example.demo.constant.ProjectMemberStatus;
import com.example.demo.constant.ProjectStatus;
import com.example.demo.dto.ProjectMember.Response.MyProjectMemberResponseDto;
import com.example.demo.dto.ProjectMember.Response.ProjectMemberDetailResponseDto;
import com.example.demo.dto.User.Response.UserMyProjectResponseDto;
import com.example.demo.dto.User.Response.UserProjectDetailResponseDto;
import com.example.demo.dto.User.Response.UserProjectResponseDto;
import com.example.demo.dto.position.Response.PositionResponseDto;
import com.example.demo.dto.project.Request.ProjectConfirmRequestDto;
import com.example.demo.dto.project.Request.ProjectParticipateRequestDto;
import com.example.demo.dto.project.Response.ProjectMeResponseDto;
import com.example.demo.dto.project.Response.ProjectSpecificDetailResponseDto;
import com.example.demo.dto.projectmemberauth.Response.ProjectMemberAuthResponseDto;
import com.example.demo.dto.trustgrade.TrustGradeDto;
import com.example.demo.dto.work.Response.WorkProjectDetailResponseDto;
import com.example.demo.global.exception.customexception.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final WorkRepository workRepository;
    private final ProjectMemberAuthRepository projectMemberAuthRepository;
    private final PositionRepository positionRepository;
    private final AlertRepository alertRepository;

    /**
     * 내 프로젝트 목록 조회
     * TODO : 현재 유저 가져오기.
     * @return
     */

    @Transactional(readOnly = true)
    public List<ProjectMeResponseDto> getMyProjects() {
        User user =
                userRepository.findById(1L).orElseThrow(() -> UserCustomException.NOT_FOUND_USER);
        List<Project> projects = projectRepository.findByUser(user);
        List<ProjectMeResponseDto> result = new ArrayList<>();

        for (Project project : projects) {
            TrustGradeDto trustGradeDto = TrustGradeDto.of(project.getTrustGrade());

            List<MyProjectMemberResponseDto> myProjectMemberResponseDtos = new ArrayList<>();
            for (ProjectMember projectMember : project.getProjectMembers()) {
                UserMyProjectResponseDto userMyProjectResponseDto =
                        UserMyProjectResponseDto.of(projectMember.getUser());
                MyProjectMemberResponseDto myProjectMemberResponseDto =
                        MyProjectMemberResponseDto.of(projectMember, userMyProjectResponseDto);
                myProjectMemberResponseDtos.add(myProjectMemberResponseDto);
            }

            ProjectMeResponseDto projectMeResponseDto =
                    ProjectMeResponseDto.of(project, trustGradeDto, myProjectMemberResponseDtos);
            result.add(projectMeResponseDto);
        }

        return result;
    }

    @Transactional(readOnly = true)
    public ProjectSpecificDetailResponseDto getDetail(Long projectId) {
        Project project =
                projectRepository
                        .findById(projectId)
                        .orElseThrow(() -> ProjectCustomException.NOT_FOUND_PROJECT);
        TrustGradeDto trustGradeDto = TrustGradeDto.of(project.getTrustGrade());
        UserProjectResponseDto userProjectResponseDto = UserProjectResponseDto.of(project);

        // ProjectMember 부분
        List<ProjectMember> projectMembers =
                projectMemberRepository
                        .findProjectsMemberByProject(project)
                        .orElseThrow(() -> ProjectMemberCustomException.NOT_FOUND_PROJECT_MEMBER);
        List<ProjectMemberDetailResponseDto> projectMemberDetailResponseDtos = new ArrayList<>();

        for (ProjectMember projectMember : projectMembers) {
            UserProjectDetailResponseDto userProjectDetailResponseDto =
                    UserProjectDetailResponseDto.of(projectMember.getUser());
            ProjectMemberAuthResponseDto projectMemberAuthResponseDto =
                    ProjectMemberAuthResponseDto.of(projectMember.getProjectMemberAuth());
            PositionResponseDto positionResponseDto =
                    PositionResponseDto.of(projectMember.getPosition());

            ProjectMemberDetailResponseDto projectMemberDetailResponseDto =
                    ProjectMemberDetailResponseDto.of(
                            projectMember,
                            userProjectDetailResponseDto,
                            projectMemberAuthResponseDto,
                            positionResponseDto);
            projectMemberDetailResponseDtos.add(projectMemberDetailResponseDto);
        }

        // work 부분
        List<Work> works =
                workRepository
                        .findWorksByProject(project)
                        .orElseThrow(() -> WorkCustomException.NOT_FOUND_WORK);
        List<WorkProjectDetailResponseDto> workProjectDetailResponseDtos = new ArrayList<>();
        for (Work work : works) {
            UserProjectDetailResponseDto userProjectDetailResponseDto =
                    UserProjectDetailResponseDto.of(work.getAssignedUserId());
            WorkProjectDetailResponseDto workProjectDetailResponseDto =
                    WorkProjectDetailResponseDto.of(work, userProjectDetailResponseDto);
            workProjectDetailResponseDtos.add(workProjectDetailResponseDto);
        }

        return ProjectSpecificDetailResponseDto.of(
                project,
                trustGradeDto,
                userProjectResponseDto,
                projectMemberDetailResponseDtos,
                workProjectDetailResponseDtos);
    }

    /**
     * 참여하기 참여하는 경우 알림보내기
     *
     * @param projectId
     * @param projectParticipateRequestDto
     */
    public void sendParticipateAlert(
            Long projectId, ProjectParticipateRequestDto projectParticipateRequestDto) {
        Project project =
                projectRepository
                        .findById(projectId)
                        .orElseThrow(() -> ProjectCustomException.NOT_FOUND_PROJECT);
        User user =
                userRepository.findById(1L).orElseThrow(() -> UserCustomException.NOT_FOUND_USER);
        Alert alert =
                Alert.builder()
                        .project(project)
                        .user(user)
                        .content("프로젝트 지원했습니다.")
                        .type(AlertType.RECRUIT)
                        .checked_YN(false)
                        .build();

        alertRepository.save(alert);
    }

    /**
     * 참여 수락하기
     *
     * @param projectId
     * @param projectConfirmRequestDto
     */
    public void confirm(Long projectId, ProjectConfirmRequestDto projectConfirmRequestDto) {
        Project project =
                projectRepository
                        .findById(projectId)
                        .orElseThrow(() -> ProjectCustomException.NOT_FOUND_PROJECT);
        User user =
                userRepository.findById(1L).orElseThrow(() -> UserCustomException.NOT_FOUND_USER);

        ProjectMemberAuth projectMemberAuth = projectMemberAuthRepository.findTopByOrderByIdDesc().orElseThrow(() -> ProjectMemberAuthCustomException.NOT_FOUND_PROJECT_MEMBER_AUTH);

        Position position =
                positionRepository
                        .findById(projectConfirmRequestDto.getPositionId())
                        .orElseThrow(() -> PositionCustomException.NOT_FOUND_POSITION);

        ProjectMember projectMember =
                ProjectMember.builder()
                        .project(project)
                        .user(user)
                        .projectMemberAuth(projectMemberAuth)
                        .status(ProjectMemberStatus.PARTICIPATING)
                        .position(position)
                        .build();

        projectMemberRepository.save(projectMember);
    }

    /**
     * 프로젝트 종료하기
     *
     * @param projectId
     */
    public void end(Long projectId) {
        Project project =
                projectRepository
                        .findById(projectId)
                        .orElseThrow(() -> ProjectCustomException.NOT_FOUND_PROJECT);

        project =
                Project.builder()
                        .name(project.getName())
                        .subject(project.getSubject())
                        .trustGrade(project.getTrustGrade())
                        .user(project.getUser())
                        .status(ProjectStatus.FINISH)
                        .crewNumber(project.getCrewNumber())
                        .startDate(project.getStartDate())
                        .endDate(project.getEndDate())
                        .build();

        projectRepository.save(project);
    }
}
