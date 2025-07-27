package com.sparta.baro_15.service;

import com.sparta.baro_15.domain.UserEntity;
import com.sparta.baro_15.domain.UserRole;
import com.sparta.baro_15.dto.reqSignupDto;
import com.sparta.baro_15.exception.CustomException;
import com.sparta.baro_15.exception.ExceptionCode;
import com.sparta.baro_15.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final String ADMIN_TOKEN = "AAABnvxRVVZXgUn stretchy";

    @Override
    public void signUp(reqSignupDto dto) {

        String username = dto.getUsername();
        String password = dto.getPassword();
        String email = dto.getEmail();
        UserRole role = dto.getRole();
        String adminToken = dto.getAdminToken();

        // 1. 사용자 이름 중복 확인
        if (userRepository.findByUsername(username).isPresent()) {
            throw new CustomException(ExceptionCode.USER_ALREADY_EXISTS);
        }

        // 2. 이메일 중복 확인
        if (userRepository.findByEmail(email).isPresent()) {
            throw new CustomException(ExceptionCode.USER_ALREADY_EXISTS);
        }

        // 3. 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(password);

        // 4. 사용자 권한 설정 및 관리자 토큰 유효성 검사
        if (role == UserRole.ADMIN) {
            if (adminToken == null || !ADMIN_TOKEN.equals(adminToken)) {
                throw new CustomException(ExceptionCode.INVALID_ADMIN_TOKEN);
            }
        }

        // 5. UserEntity 객체 생성 및 저장
        UserEntity user = UserEntity.builder()
                .username(username)
                .password(encodedPassword)
                .email(email)
                .role(role)
                .build();

        userRepository.save(user);
    }
}
