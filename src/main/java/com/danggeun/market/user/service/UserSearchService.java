package com.danggeun.market.user.service;

import com.danggeun.market.user.domain.User;
import com.danggeun.market.user.domain.UserRepository;
import com.danggeun.market.user.dto.UserDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserSearchService {
    private final UserRepository userRepository;

    public UserDetailResponse searchUserInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 id의 유저가 존재하지 않습니다.");
                });
        return UserDetailResponse.fromEntity(user);
    }
}
