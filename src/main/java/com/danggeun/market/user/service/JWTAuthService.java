package com.danggeun.market.user.service;

import com.danggeun.market.common.auth.JwtUtils;
import com.danggeun.market.user.service.dto.AuthLoginCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JWTAuthService {
    private final AuthenticationProvider authenticationProvider;
    private final JwtUtils jwtUtils;

    public String authenticate(AuthLoginCommand authLoginCommand) {
        Authentication authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(authLoginCommand.getUsername(), authLoginCommand.getPassword(), null));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtils.publishTokenTo(userDetails.getUsername());
    }
}
