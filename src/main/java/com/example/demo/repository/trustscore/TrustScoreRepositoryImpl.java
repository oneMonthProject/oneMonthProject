package com.example.demo.repository.trustscore;

import com.example.demo.model.trustscore.QTrustScore;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TrustScoreRepositoryImpl implements TrustScoreRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public void updateScore(Long userId, int score) {
        QTrustScore trustScore = QTrustScore.trustScore;
        jpaQueryFactory.update(trustScore)
                .set(trustScore.score,
                        new CaseBuilder()
                                .when(trustScore.score.add(score).lt(0))
                                .then(0)
                                .otherwise(trustScore.score.add(score)))
                .where(trustScore.userId.eq(userId))
                .execute();
    }
    @Override
    public int getUserScore(Long userId) {
        QTrustScore trustScore = QTrustScore.trustScore;
        return jpaQueryFactory
                .select(trustScore.score)
                .from(trustScore)
                .where(trustScore.userId.eq(userId))
                .fetchFirst();
    }

    /*
    updateQuery.set(trustScore.score, SQLExpressions
        .caseBuilder()
        .when(trustScore.score.add(scoreChange).goe(0))
        .then(trustScore.score.add(scoreChange))
        .otherwise(0)


        SQLExpressions
                .caseBuilder()
                .when(trustScore.score.add(scoreChange).goe(0))
                .then(trustScore.score.add(scoreChange))
                .otherwise(0)
     */

//    public Integer getScoreByUserId(Long userId) {
//        QTrustScore trustScore = QTrustScore.trustScore;
//        return query
//                .select(trustScore.score)
//                .from(trustScore)
//                .where(trustScore.userId.eq(userId))
//                .fetchFirst();
//    }
//
//    @Override
//    public void updateScore(Long userId, Integer scoreChange) {
//        QTrustScore trustScore = QTrustScore.trustScore;
//        query.update(trustScore)
//                .where(trustScore.userId.eq(userId))
//                .set(trustScore.score, trustScore.score.add(scoreChange))
//                .execute();
//    }
}
