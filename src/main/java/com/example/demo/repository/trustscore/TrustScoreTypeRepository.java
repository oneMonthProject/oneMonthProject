package com.example.demo.repository.trustscore;

import com.example.demo.model.trustscore.TrustScoreType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrustScoreTypeRepository extends JpaRepository<TrustScoreType, Long>, TrustScoreTypeRepositoryCustom
{
}
