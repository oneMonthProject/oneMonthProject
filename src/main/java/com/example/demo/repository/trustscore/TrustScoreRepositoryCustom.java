package com.example.demo.repository.trustscore;

public interface TrustScoreRepositoryCustom {
    void updateScore(Long userId, int score);
    int getUserScore(Long userId);
}
