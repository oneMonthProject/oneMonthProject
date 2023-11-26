package com.example.demo.controller.trustscore;

import com.example.demo.dto.Common.ResponseDto;
import com.example.demo.dto.trustscore.request.TrustScoreUpdateRequestDto;
import com.example.demo.dto.trustscore.response.TrustScoreUpdateResponseDto;
import com.example.demo.service.trustscore.TrustScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class TrustScoreController {
    private final TrustScoreService trustScoreService;
    @PostMapping("/api/trust-score")
    public ResponseEntity<ResponseDto<?>> updateScoreByManager(
            @RequestBody @Valid TrustScoreUpdateRequestDto requestDto) {
        TrustScoreUpdateResponseDto responseDto = trustScoreService.updateTrustScore(requestDto);
        return new ResponseEntity<>(new ResponseDto<>("success", responseDto), HttpStatus.OK);
    }
}
