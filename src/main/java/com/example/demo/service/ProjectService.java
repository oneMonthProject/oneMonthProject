package com.example.demo.service;

import com.example.demo.dto.Position.Response.PositionResponseDto;
import com.example.demo.dto.Project.Response.ProjectMeResponseDto;
import com.example.demo.dto.ProjectMember.Response.MyProjectMemberResponseDto;
import com.example.demo.dto.ProjectMemberAuth.Response.ProjectMemberAuthResponseDto;
import com.example.demo.dto.TrustGrade.TrustGradeDto;
import com.example.demo.dto.User.Response.UserMyProjectResponseDto;
import com.example.demo.global.exception.customexception.UserCustomException;
import com.example.demo.model.Project;
import com.example.demo.model.ProjectMember;
import com.example.demo.model.User;
import com.example.demo.repository.ProjectMemberRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserRepository;
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
}
