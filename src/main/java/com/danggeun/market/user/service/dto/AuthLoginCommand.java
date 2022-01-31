package com.danggeun.market.user.service.dto;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class AuthLoginCommand {
    private String username;
    private String password;

    public AuthLoginCommand(String username, String password) {
        checkArgument(isNotEmpty(username), "username은 공백일 수 없습니다.");
        checkArgument(isNotEmpty(password), "password는 공백일 수 없습니다.");
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
