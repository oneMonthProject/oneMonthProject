package com.example.demo.repository.trust_score;

import java.util.List;

public interface TrustScoreTypeRepositoryCustom {
    /**
     * 프로젝트가 아닌 경우 점수조회
     *
     * @param trustScoreTypeId
     * @return
     */
    int getScore(Long trustScoreTypeId);

    /**
     * 프로젝트에 해당하는 점수 조회
     *
     * @param trueScoreTypeId
     * @return
     */
    int getScoreByProject(Long projectId, Long trueScoreTypeId);

    /**
     * 신뢰점수타입 대분류 아이디 조회
     *
     * @return List<Long>
     */
    List<Long> findAllUpScoreTypeId();
}
