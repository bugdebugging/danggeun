package com.danggeun.market.user.domain;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@EqualsAndHashCode
public class UserAuthority {
    @Column
    private String authority;

    protected UserAuthority() {
    }

    private UserAuthority(String authority) {
        this.authority = authority;
    }

    public static UserAuthority of(String authority) {
        return new UserAuthority(authority);
    }

    public String getAuthority() {
        return authority;
    }
}
