package com.danggeun.market.user.controller;

import com.danggeun.market.common.api.ApiResult;
import com.danggeun.market.common.api.ApiUtils;
import com.danggeun.market.user.dto.AuthLoginRequest;
import com.danggeun.market.user.dto.AuthSignUpRequest;
import com.danggeun.market.user.service.JWTAuthService;
import com.danggeun.market.user.service.UserAuthService;
import com.danggeun.market.user.service.dto.AuthLoginCommand;
import com.danggeun.market.user.service.dto.AuthSignUpCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserAuthController {
    private final UserAuthService userAuthService;
    private final JWTAuthService jwtAuthService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/api/auth/login")
    public ResponseEntity login(@RequestBody AuthLoginRequest authLoginRequest) {
        String token = jwtAuthService.authenticate(new AuthLoginCommand(authLoginRequest.getUsername(), authLoginRequest.getPassword()));
        return ResponseEntity.status(HttpStatus.OK)
                .header("Authorization", token)
                .build();
    }

    @PostMapping("/api/auth/signup")
    public ApiResult signUp(@RequestBody AuthSignUpRequest authSignUpRequest) {
        AuthSignUpCommand authSignUpCommand = new AuthSignUpCommand(authSignUpRequest.getEmail(),
                passwordEncoder.encode(authSignUpRequest.getPassword()),
                authSignUpRequest.getName(),
                authSignUpRequest.getPhone(),
                authSignUpRequest.getNickname());
        userAuthService.singUpForNewUser(authSignUpCommand);
        return ApiUtils.success("success");
    }
}
