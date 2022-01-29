package com.danggeun.market.common.auth;

import com.danggeun.market.user.dto.AuthLoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JWTAuthService {
    private final AuthenticationProvider authenticationProvider;

    public String authenticate(AuthLoginRequest authLoginRequest){

    }
}
