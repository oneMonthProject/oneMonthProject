package com.example.demo.repository.trustscore;

import com.example.demo.dto.trustscorehistory.ProjectUserHistoryDto;

import java.util.List;

public interface TrustScoreHistoryRepositoryCustom {
    List<ProjectUserHistoryDto> getProjectUserHistory(Long projectId, Long userId);
}
