package com.danggeun.market.user.service;

import com.danggeun.market.user.domain.User;
import com.danggeun.market.user.domain.UserImage;
import com.danggeun.market.user.domain.UserRepository;
import com.danggeun.market.user.dto.UserSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserProfileChangeService {
    private final UserRepository userRepository;

    public UserSummaryResponse changeProfile(Long userId, UserImage userImage, String nickname) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 id의 사용자가 존재하지 않습니다.");
                });
        user.changeProfile(userImage, nickname);
        return UserSummaryResponse.fromEntity(user);
    }
}
