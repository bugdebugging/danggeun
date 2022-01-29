package com.danggeun.market.user.service;

import com.danggeun.market.common.auth.UserAuthWrapper;
import com.danggeun.market.user.domain.User;
import com.danggeun.market.user.domain.UserRepository;
import com.danggeun.market.user.dto.UserDetailResponse;
import com.danggeun.market.user.service.dto.AuthSignUpCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserAuthService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailResponse singUpForNewUser(AuthSignUpCommand authSignUpCommand) {
        //email 중복 체크 해야함.

        User user = new User(authSignUpCommand.getEmail(),
                authSignUpCommand.getPassword(),
                authSignUpCommand.getName(),
                authSignUpCommand.getPhone(),
                authSignUpCommand.getNickname());

        userRepository.save(user);
        return UserDetailResponse.fromEntity(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 email의 유저가 존재하지 않습니다.");
                });

        return new UserAuthWrapper(user);
    }
}
