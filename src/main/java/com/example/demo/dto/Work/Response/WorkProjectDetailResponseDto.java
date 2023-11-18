package com.example.demo.dto.Work.Response;

import com.example.demo.dto.User.Response.UserProjectDetailResponseDto;
import com.example.demo.model.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
@Builder
public class WorkProjectDetailResponseDto {
    private Long workId;
    private UserProjectDetailResponseDto assginedUser;
    private ProjectMember lastModifiedMember;
    private String workContent;
    private Boolean expireStatus;
    private Boolean completeStatus;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public static WorkProjectDetailResponseDto of(Work work, UserProjectDetailResponseDto userProjectDetailResponseDto) {
        return WorkProjectDetailResponseDto.builder()
                .workId(work.getId())
                .assginedUser(userProjectDetailResponseDto)
                .lastModifiedMember(work.getLastModifiedMember())
                .workContent(work.getContent())
                .expireStatus(work.isExpireStatus())
                .completeStatus(work.isCompletionStatus())
                .startDate(work.getStartDate())
                .endDate(work.getEndDate())
                .createDate(work.getCreateDate())
                .updateDate(work.getUpdateDate())
                .build();
    }
}
