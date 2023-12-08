package com.example.demo.controller.trust_score;

import com.example.demo.dto.common.ResponseDto;
import com.example.demo.dto.trust_score_type.TrustScoreTypeSearchCriteria;
import com.example.demo.dto.trust_score_type.response.TrustScoreTypeReadResponseDto;
import com.example.demo.service.trust_score.TrustScoreTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TrustScoreTypeController {
    private final TrustScoreTypeService trustScoreTypeService;
    @GetMapping("/api/trust-score-type")
    public ResponseEntity<ResponseDto<?>> getAll() {
        List<TrustScoreTypeReadResponseDto> dto = trustScoreTypeService.getAllAndReturnDto();
        return new ResponseEntity<>(ResponseDto.success("success",dto), HttpStatus.OK);
    }

    @GetMapping("/api/trust-score-type/search")
    public ResponseEntity<ResponseDto<?>> getSearchResults(
            @RequestParam(name = "isDeleted", required = false) Boolean isDeleted,
            @RequestParam(name = "isParentType", required = false) Boolean isParentType,
            @RequestParam(name = "trustGrade", required = false) List<String> trustGrade,
            @RequestParam(name = "parentTypeId", required = false) List<Long> parentTypeId,
            @RequestParam(name = "gubunCode", required = false) String gubunCode
    ) {
        TrustScoreTypeSearchCriteria criteria =
                TrustScoreTypeSearchCriteria.builder()
                        .isParentType(isDeleted)
                        .isParentType(isParentType)
                        .trustGrade(trustGrade)
                        .parentTypeId(parentTypeId)
                        .gubunCode(gubunCode)
                        .build();

        List<TrustScoreTypeReadResponseDto> searchResults = trustScoreTypeService.getSearchResults(criteria);
        return new ResponseEntity<>(ResponseDto.success("success", searchResults), HttpStatus.OK);
    }
}
