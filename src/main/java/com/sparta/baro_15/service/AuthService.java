package com.sparta.baro_15.service;

import com.sparta.baro_15.dto.ReqSigninDto;
import com.sparta.baro_15.dto.ReqSignupDto;

public interface AuthService {
    void signUp(ReqSignupDto dto);

    String signIn(ReqSigninDto reqDto);
}
