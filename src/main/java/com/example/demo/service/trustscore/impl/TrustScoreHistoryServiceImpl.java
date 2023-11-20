package com.example.demo.service.trustscore.impl;

import com.example.demo.repository.trustscore.TrustScoreHistoryRepository;
import com.example.demo.service.trustscore.TrustScoreHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrustScoreHistoryServiceImpl implements TrustScoreHistoryService {
    private final TrustScoreHistoryRepository trustScoreHistoryRepository;
}
