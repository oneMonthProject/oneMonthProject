package com.example.demo.repository.trustscore;

import com.example.demo.model.trustscore.TrustScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrustScoreRepository extends JpaRepository<TrustScore, Long>, TrustScoreRepositoryCustom {
}
