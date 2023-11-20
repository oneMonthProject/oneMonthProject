package com.example.demo.service.trustscore.impl;

import com.example.demo.repository.trustscore.TrustScoreRepository;
import com.example.demo.service.trustscore.TrustScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrustScoreServiceImpl implements TrustScoreService {
    private final TrustScoreRepository trustScoreRepository;
}
