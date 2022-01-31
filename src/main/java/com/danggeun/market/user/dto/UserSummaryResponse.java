package com.danggeun.market.user.dto;

import com.danggeun.market.user.domain.User;
import com.danggeun.market.user.domain.UserImage;

public class UserSummaryResponse {
    private Long id;
    private String nickname;
    private UserImage profile;

    private UserSummaryResponse(Long id, String nickname, UserImage profile) {
        this.id = id;
        this.nickname = nickname;
        this.profile = profile;
    }

    public static UserSummaryResponse fromEntity(User user) {
        return new UserSummaryResponse(user.getId(), user.getNickname(), user.getUserImage());
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public UserImage getProfile() {
        return profile;
    }
}
