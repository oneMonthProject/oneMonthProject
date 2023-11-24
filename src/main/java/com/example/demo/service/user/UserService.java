package com.example.demo.service.user;

import com.example.demo.dto.common.ResponseDto;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

    // 이메일 중복확인 로직
    @Transactional(readOnly = true)
    ResponseDto<?> checkEmail(String email);
}
