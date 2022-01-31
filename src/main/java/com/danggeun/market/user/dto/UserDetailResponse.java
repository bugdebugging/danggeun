package com.danggeun.market.user.dto;

import com.danggeun.market.user.domain.User;

import java.time.LocalDateTime;

public class UserDetailResponse {
    private Long id;
    private String email;
    private String name;
    private String phone;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String profileImage;

    public UserDetailResponse(Long id, String email, String name, String phone, String nickname, LocalDateTime createdAt, LocalDateTime updatedAt, String profileImage) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.nickname = nickname;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.profileImage = profileImage;
    }

    public static UserDetailResponse fromEntity(User user) {
        return new UserDetailResponse(user.getId()
                , user.getEmail()
                , user.getName()
                , user.getPhone()
                , user.getNickname()
                , user.getCreatedAt()
                , user.getUpdatedAt()
                , user.getUserImage() == null ? null : user.getUserImage().getImageUrl());
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getNickname() {
        return nickname;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getProfileImage() {
        return profileImage;
    }
}
