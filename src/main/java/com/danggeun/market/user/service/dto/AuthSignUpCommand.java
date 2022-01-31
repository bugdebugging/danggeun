package com.danggeun.market.user.service.dto;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class AuthSignUpCommand {
    private String email;
    private String password;
    private String name;
    private String phone;
    private String nickname;

    public AuthSignUpCommand(String email, String password, String name, String phone, String nickname) {
        checkArgument(isNotEmpty(email), "email은 공백일 수 없습니다.");
        checkArgument(isNotEmpty(password), "password는 공백일 수 없습니다.");
        checkArgument(isNotEmpty(name), "name은 공백일 수 없습니다.");
        checkArgument(isNotEmpty(phone), "phone은 공백일 수 없습니다.");
        checkArgument(isNotEmpty(nickname), "nickname은 공백일 수 없습니다.");

        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.nickname = nickname;
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
