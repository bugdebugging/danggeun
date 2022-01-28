package com.danggeun.market.user.dto;

public class AuthSignUpRequest {
    private String email;
    private String password;
    private String name;
    private String phone;
    private String nickname;

    public AuthSignUpRequest() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
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
}
