package com.danggeun.market.user.dto;

public class UserProfileChangeRequest {
    private String nickname;

    public UserProfileChangeRequest() {
    }

    public UserProfileChangeRequest(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
