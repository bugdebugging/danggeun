package com.danggeun.market.user.service;

import com.danggeun.market.common.auth.UserAuthWrapper;
import com.danggeun.market.user.domain.User;
import com.danggeun.market.user.domain.UserRepository;
import com.danggeun.market.user.dto.UserDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailResponse searchUserInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 id의 유저가 존재하지 않습니다.");
                });
        return UserDetailResponse.fromEntity(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Authority를 패치조인으로 들고와서 N+1문제를 막아야한다.
        User user = userRepository.findUserByEmail(username)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 email의 유저가 존재하지 않습니다.");
                });

        return new UserAuthWrapper(user);
    }
}
