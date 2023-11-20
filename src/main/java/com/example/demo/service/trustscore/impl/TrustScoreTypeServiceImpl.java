package com.example.demo.service.trustscore.impl;

import com.example.demo.repository.trustscore.TrustScoreHistoryRepository;
import com.example.demo.service.trustscore.TrustScoreTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrustScoreTypeServiceImpl implements TrustScoreTypeService {
    private final TrustScoreHistoryRepository trustScoreHistoryRepository;
}
