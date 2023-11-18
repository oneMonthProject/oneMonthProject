package com.example.demo.service;

import com.example.demo.dto.Position.Response.PositionResponseDto;
import com.example.demo.dto.Project.Response.ProjectDetailResponseDto;
import com.example.demo.dto.Project.Response.ProjectMeResponseDto;
import com.example.demo.dto.Project.Response.ProjectSpecificDetailResponseDto;
import com.example.demo.dto.ProjectMember.Response.MyProjectMemberResponseDto;
import com.example.demo.dto.ProjectMember.Response.ProjectMemberDetailResponseDto;
import com.example.demo.dto.ProjectMemberAuth.Response.ProjectMemberAuthResponseDto;
import com.example.demo.dto.TrustGrade.TrustGradeDto;
import com.example.demo.dto.User.Response.UserMyProjectResponseDto;
import com.example.demo.dto.User.Response.UserProjectDetailResponseDto;
import com.example.demo.dto.User.Response.UserProjectResponseDto;
import com.example.demo.dto.Work.Response.WorkProjectDetailResponseDto;
import com.example.demo.global.exception.customexception.ProjectCustomException;
import com.example.demo.global.exception.customexception.ProjectMemberCustomException;
import com.example.demo.global.exception.customexception.UserCustomException;
import com.example.demo.global.exception.customexception.WorkCustomException;
import com.example.demo.model.Project;
import com.example.demo.model.ProjectMember;
import com.example.demo.model.User;
import com.example.demo.model.Work;
import com.example.demo.repository.ProjectMemberRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.WorkRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final WorkRepository workRepository;

    public List<ProjectMeResponseDto> getMyProjects(){
        User user = userRepository.findById(1L).orElseThrow(() -> UserCustomException.NOT_FOUND_USER);
        List<Project> projects = projectRepository.findByUser(user.getId());
        List<ProjectMeResponseDto> result = new ArrayList<>();

        for (Project project : projects) {
            TrustGradeDto trustGradeDto = TrustGradeDto.of(project.getTrustGrade());

            List<MyProjectMemberResponseDto> myProjectMemberResponseDtos = new ArrayList<>();
            for(ProjectMember projectMember : project.getProjectMembers()){
                UserMyProjectResponseDto userMyProjectResponseDto = UserMyProjectResponseDto.of(projectMember.getUser());
                MyProjectMemberResponseDto myProjectMemberResponseDto = MyProjectMemberResponseDto.of(projectMember, userMyProjectResponseDto);
                myProjectMemberResponseDtos.add(myProjectMemberResponseDto);
            }

            ProjectMeResponseDto projectMeResponseDto = ProjectMeResponseDto.of(project, trustGradeDto, myProjectMemberResponseDtos);
            result.add(projectMeResponseDto);
        }

        return result;
    }

    public ProjectSpecificDetailResponseDto getDetail(Long projectId){
        Project project = projectRepository.findById(projectId).orElseThrow(() -> ProjectCustomException.NOT_FOUND_PROJECT);
        TrustGradeDto trustGradeDto = TrustGradeDto.of(project.getTrustGrade());
        UserProjectResponseDto userProjectResponseDto = UserProjectResponseDto.of(project);

        //ProjectMember 부분
        List<ProjectMember> projectMembers = projectMemberRepository.findProjectsMemberByProject(project).orElseThrow(() -> ProjectMemberCustomException.NOT_FOUND_PROJECT_MEMBER);
        List<ProjectMemberDetailResponseDto> projectMemberDetailResponseDtos = new ArrayList<>();

        for (ProjectMember projectMember : projectMembers) {
            UserProjectDetailResponseDto userProjectDetailResponseDto = UserProjectDetailResponseDto.of(projectMember.getUser());
            ProjectMemberAuthResponseDto projectMemberAuthResponseDto = ProjectMemberAuthResponseDto.of(projectMember.getProjectMemberAuth());
            PositionResponseDto positionResponseDto = PositionResponseDto.of(projectMember.getPosition());

            ProjectMemberDetailResponseDto projectMemberDetailResponseDto = ProjectMemberDetailResponseDto.of(projectMember, userProjectDetailResponseDto, projectMemberAuthResponseDto, positionResponseDto);
            projectMemberDetailResponseDtos.add(projectMemberDetailResponseDto);
        }

        //work 부분
        List<Work> works = workRepository.findWorksByProject(project).orElseThrow(() -> WorkCustomException.NOT_FOUND_WORK);
        List<WorkProjectDetailResponseDto> workProjectDetailResponseDtos = new ArrayList<>();
        for (Work work : works) {
            UserProjectDetailResponseDto userProjectDetailResponseDto = UserProjectDetailResponseDto.of(work.getAssignedUserId());
            WorkProjectDetailResponseDto workProjectDetailResponseDto = WorkProjectDetailResponseDto.of(work, userProjectDetailResponseDto);
            workProjectDetailResponseDtos.add(workProjectDetailResponseDto);
        }

        return ProjectSpecificDetailResponseDto.of(
                project,
                trustGradeDto,
                userProjectResponseDto,
                projectMemberDetailResponseDtos,
                workProjectDetailResponseDtos
        );
    }
}
