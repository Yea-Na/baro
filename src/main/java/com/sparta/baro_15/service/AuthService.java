package com.sparta.baro_15.service;

import com.sparta.baro_15.domain.UserEntity;
import com.sparta.baro_15.dto.reqSignupDto;

public interface AuthService {
    void signUp(reqSignupDto dto);

}
