package com.example.demo.service.trustscore;

import com.example.demo.dto.trustscore.request.TrustScoreUpdateRequestDto;
import com.example.demo.dto.trustscore.response.TrustScoreUpdateResponseDto;

public interface TrustScoreService {
    TrustScoreUpdateResponseDto updateTrustScore(TrustScoreUpdateRequestDto requestDto);
}
