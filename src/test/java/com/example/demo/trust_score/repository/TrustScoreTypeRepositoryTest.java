package com.example.demo.trust_score.repository;


import com.example.demo.dto.trust_score_type.TrustScoreTypeSearchCriteria;
import com.example.demo.dto.trust_score_type.response.TrustScoreTypeReadResponseDto;
import com.example.demo.global.exception.customexception.TrustGradeCustomException;
import com.example.demo.global.exception.customexception.TrustScoreTypeCustomException;
import com.example.demo.model.project.Project;
import com.example.demo.model.trust_grade.TrustGrade;
import com.example.demo.model.trust_score.TrustScoreType;
import com.example.demo.repository.project.ProjectRepository;
import com.example.demo.repository.trust_grade.TrustGradeRepository;
import com.example.demo.repository.trust_score.TrustScoreTypeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static com.example.demo.constant.TrustScoreTypeIdentifier.*;

@SpringBootTest
@Transactional
public class TrustScoreTypeRepositoryTest {

    @Autowired
    private TrustScoreTypeRepository trustScoreTypeRepository;

    @Autowired
    private TrustGradeRepository trustGradeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    @DisplayName("getScore 메서드 테스트 - 성공")
    public void getScore_MethodTest_Pass() {
        // given
        Long scoreTypeId = NEW_MEMBER;

        // when
        int score = trustScoreTypeRepository.getScore(scoreTypeId);

        // then
        Assertions.assertThat(score).isEqualTo(200);
    }

    @Test
    @DisplayName("getScore 메서드 테스트 - 실패. " +
            "원인 : 존재하지 않는 신뢰점수타입 식별자")
    public void getScore_MethodTest_Fail() {
        // given
        Long scoreTypeId = 1000000L;

        // when - then
        Assertions.assertThatThrownBy(
                () -> trustScoreTypeRepository.getScore(scoreTypeId))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("getScoreByProject 메서드 테스트 - 성공")
    public void getScoreByProject_MethodTest_Pass() {
        // given
        // 4등급 프로젝트 생성
        TrustGrade trustGrade = trustGradeRepository.findById(4L)
                .orElseThrow(() -> TrustGradeCustomException.NOT_FOUND_TRUST_GRADE);
        Project project = Project.builder()
                .name("테스트 프로젝트")
                .trustGrade(trustGrade)
                .build();
        Project saveProject = projectRepository.save(project);

        // when
        Long trustScoreTypeId = WORK_COMPLETE;
        int scoreByProject = trustScoreTypeRepository
                .getScoreByProject(saveProject.getId(), trustScoreTypeId);

        // then
        Assertions.assertThat(scoreByProject).isEqualTo(20);
    }

    @Test
    @DisplayName("getScoreByProject 메서드 테스트 - 실패 " +
            "원인 : 해당 프로젝트 존재하지 않음")
    public void getScoreByProject_MethodTest_Fail() {
        // given
        Long projectId = 10000000000000L;
        Long trustScoreTypeId = WORK_COMPLETE;

        // when - then
        Assertions.assertThatThrownBy(
                () -> trustScoreTypeRepository.getScoreByProject(projectId, trustScoreTypeId))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("findAllUpScoreTypeId 메서드 테스트 - 성공")
    public void findAllUpScoreTypeId_MethodTest_Pass() {
        // given
        List<Long> scoreTypeIdentifierList = new ArrayList<>(Arrays.asList(WORK_COMPLETE, WORK_INCOMPLETE,
                NEW_MEMBER, SELF_WITHDRAWAL, FORCE_WITHDRAWAL, LATE_WORK));

        // when
        List<Long> upScoreTypeIdList =
                trustScoreTypeRepository.findAllUpScoreTypeId();

        // then
        Assertions.assertThat(scoreTypeIdentifierList)
                .isEqualTo(upScoreTypeIdList);
    }
    // TODO : 더 나은 테스트 방식 고민
    @Test
    @DisplayName("신뢰점수타입 전체 조회")
    public void findAllTrustScoreTypes_Method_Test_Pass() {
        // when
        List<TrustScoreType> allTrustScoreTypes = trustScoreTypeRepository.findAll();

        // then
        Assertions.assertThat(allTrustScoreTypes.size()).isEqualTo(26);
    }
    // TODO : 더 나은 테스트 방식 고민
    @Test
    @DisplayName("신뢰점수타입 참조 작동 여부 확인")
    public void UpTrustScoreType_Contains_Children_MappedBy_Test() {
        // given
        TrustScoreType parent = trustScoreTypeRepository.findById(WORK_COMPLETE)
                .orElseThrow(() -> TrustScoreTypeCustomException.NOT_FOUND_TRUST_SCORE_TYPE);

        // when
        List<TrustScoreType> children = parent.getSubTrustScoreTypes();


        // then
        Assertions.assertThat(children.size()).isEqualTo(4);

    }

    @Test
    @DisplayName("검색 조건 없음 - 전체 조회")
    public void getSearchResults_NoCriteria_Pass() {
        // given
        TrustScoreTypeSearchCriteria criteria = new TrustScoreTypeSearchCriteria();

        // when
        List<TrustScoreTypeReadResponseDto> searchResults =
                trustScoreTypeRepository.findSearchResults(criteria);

        // then
        Assertions.assertThat(searchResults.size()).isEqualTo(26);

    }

    @Test
    @DisplayName("검색 결과 없음")
    public void getSearchResults_NewMember_firstTrustGrade_NoResult_Pass() {
        // given
        TrustScoreTypeSearchCriteria criteria = new TrustScoreTypeSearchCriteria();
        List<Long> newMember = Collections.singletonList(3L);
        List<String> firstTrustGrade = Collections.singletonList("1등급");
        criteria.setParentTypeId(newMember);
        criteria.setTrustGrade(firstTrustGrade);

        // when
        List<TrustScoreTypeReadResponseDto> searchResults =
                trustScoreTypeRepository.findSearchResults(criteria);

        // then
        Assertions.assertThat(searchResults).isEmpty();

    }

    @Test
    @DisplayName("상위신뢰점수타입 조회")
    public void getSearchResults_isParentType() {
        // given
        TrustScoreTypeSearchCriteria criteria = new TrustScoreTypeSearchCriteria();
        criteria.setIsParentType(true);

        // when
        List<TrustScoreTypeReadResponseDto> searchResults =
                trustScoreTypeRepository.findSearchResults(criteria);

        // then
        Assertions.assertThat(searchResults.size()).isEqualTo(6);

    }

    @Test
    @DisplayName("구분코드가 P 신뢰점수타입 조회")
    public void getSearchResults_gubunCodePlus() {
        // given
        TrustScoreTypeSearchCriteria criteria = new TrustScoreTypeSearchCriteria();
        criteria.setGubunCode("P");

        // when
        List<TrustScoreTypeReadResponseDto> searchResults =
                trustScoreTypeRepository.findSearchResults(criteria);

        // then
        Assertions.assertThat(searchResults.size()).isEqualTo(6);

    }
    @Test
    @DisplayName("1등급, 2등급 신뢰점수타입 조회")
    public void getSearchResults_FirstAndSecondGrade_Test_Pass() {
        // given
        TrustScoreTypeSearchCriteria criteria = new TrustScoreTypeSearchCriteria();
        List<String> grades = new ArrayList<>();
        grades.add("1등급");
        grades.add("2등급");
        criteria.setTrustGrade(grades);

        // when
        List<TrustScoreTypeReadResponseDto> searchResults = trustScoreTypeRepository.findSearchResults(criteria);

        // then
        Assertions.assertThat(searchResults.size()).isEqualTo(10);

    }
    @Test
    @DisplayName("1등급, 2등급 및 신규회원, 강제탈퇴 신뢰점수타입 조회")
    public void getSearchResults_FirstAndSecondGrade_NewMemberAndForceWithdrawal_Test_Pass() {
        // given
        TrustScoreTypeSearchCriteria criteria = new TrustScoreTypeSearchCriteria();

        List<String> grades = new ArrayList<>();
        grades.add("1등급");
        grades.add("2등급");
        criteria.setTrustGrade(grades);

        List<Long> ids = new ArrayList<>();
        ids.add(NEW_MEMBER);
        ids.add(FORCE_WITHDRAWAL);
        criteria.setParentTypeId(ids);

        // when
        List<TrustScoreTypeReadResponseDto> searchResults = trustScoreTypeRepository.findSearchResults(criteria);

        // then
        Assertions.assertThat(searchResults.size()).isEqualTo(2);


    }

    @Test
    @DisplayName("1등급, 2등급 및 신규회원, 강제탈퇴 신뢰점수타입 조회")
    public void getSearchResults_NewMemberAndForceWithdrawal_Test_Pass() {
        // given
        TrustScoreTypeSearchCriteria criteria = new TrustScoreTypeSearchCriteria();

        List<Long> ids = new ArrayList<>();
        ids.add(NEW_MEMBER);
        ids.add(FORCE_WITHDRAWAL);
        criteria.setParentTypeId(ids);

        // when
        List<TrustScoreTypeReadResponseDto> searchResults = trustScoreTypeRepository.findSearchResults(criteria);

        // then
        Assertions.assertThat(searchResults.size()).isEqualTo(6);
    }
}
