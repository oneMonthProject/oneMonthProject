package com.example.demo.repository.trustscore;

import com.example.demo.model.trustscore.TrustScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TrustScoreRepository extends JpaRepository<TrustScore, Long>, TrustScoreRepositoryCustom {
}
