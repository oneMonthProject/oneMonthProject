package com.example.demo.service.project;

import com.example.demo.constant.AlertType;
import com.example.demo.dto.position.response.PositionResponseDto;
import com.example.demo.dto.projectmember.response.ProjectMemberReadCrewDetailResponseDto;
import com.example.demo.dto.trust_grade.response.TrustGradeResponseDto;
import com.example.demo.dto.user.response.UserCrewDetailResponseDto;
import com.example.demo.global.exception.customexception.ProjectMemberCustomException;
import com.example.demo.model.alert.Alert;
import com.example.demo.model.project.ProjectMember;
import com.example.demo.repository.alert.AlertRepository;
import com.example.demo.repository.project.ProjectMemberRepository;
import com.example.demo.repository.project.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProjectMemberService {
    private final ProjectMemberRepository projectMemberRepository;
    private final AlertRepository alertRepository;
    private final ProjectRepository projectRepository;

    /**
     * 프로젝트 멤버 탈퇴 알림 보내기
     *
     * @param projectMemberId
     */
    public void sendWithdrawlAlert(Long projectMemberId) {
        ProjectMember projectMember =
                projectMemberRepository
                        .findById(projectMemberId)
                        .orElseThrow(() -> ProjectMemberCustomException.NOT_FOUND_PROJECT_MEMBER);

        Alert alert =
                Alert.builder()
                        .project(projectMember.getProject())
                        .checkUser(projectMember.getProject().getUser())
                        .applyUser(projectMember.getUser())
                        .content("프로젝트 탈퇴")
                        .type(AlertType.WITHDRWAL)
                        .checked_YN(false)
                        .build();

        alertRepository.save(alert);
    }

    /**
     * 프로젝트 멤버 탈퇴 수락하기
     *
     * @param projectMemberId
     */
    public void withdrawlConfirm(Long projectMemberId) {
        ProjectMember projectMember =
                projectMemberRepository
                        .findById(projectMemberId)
                        .orElseThrow(() -> ProjectMemberCustomException.NOT_FOUND_PROJECT_MEMBER);
        projectMemberRepository.delete(projectMember);
    }

    /**
     * 프로젝트 멤버 강제 탈퇴하기.
     *
     * @param projectMemberId
     */
    public void withdrawlForce(Long projectMemberId) {
        ProjectMember projectMember =
                projectMemberRepository
                        .findById(projectMemberId)
                        .orElseThrow(() -> ProjectMemberCustomException.NOT_FOUND_PROJECT_MEMBER);
        projectMemberRepository.delete(projectMember);
    }

    /**
     * 크루정보 상세 페이지
     * 유저 정보들, 유저 기술들, 프로젝트 개수, 신뢰점수 이력들
     * @param projectMemberId
     */
    public ProjectMemberReadCrewDetailResponseDto getCrewDetail(Long projectMemberId){
        ProjectMember projectMember = projectMemberRepository.findById(projectMemberId).orElseThrow(() -> ProjectMemberCustomException.NOT_FOUND_PROJECT_MEMBER);

        int projectCount = projectRepository.countProjectByProjectMembers(projectMember);
        TrustGradeResponseDto trustGradeResponseDto = TrustGradeResponseDto.of(projectMember.getProject().getTrustGrade());
        PositionResponseDto positionResponseDto = PositionResponseDto.of(projectMember.getPosition());
        UserCrewDetailResponseDto userCrewDetailResponseDto = UserCrewDetailResponseDto.of(projectMember.getUser(),positionResponseDto, trustGradeResponseDto);

        return ProjectMemberReadCrewDetailResponseDto.of(
                projectMember,
                projectCount,
                userCrewDetailResponseDto,
                positionResponseDto
        );
    }
}
