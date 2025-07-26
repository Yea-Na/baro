package com.sparta.baro_15.controller;

import com.sparta.baro_15.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("")
public class AuthController {

    private final AuthService userService;

    //회원가입
    @PostMapping()
    public void signup(){

    }

    //로그인
    @PostMapping()
    public void signin(){

    }
}
