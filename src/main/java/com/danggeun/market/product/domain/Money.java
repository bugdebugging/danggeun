package com.danggeun.market.product.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Money {
    private Long money;
    private static final Money zero = Money.of(0L);

    protected Money() {
    }

    private Money(Long money) {
        this.money = money;
    }


    public static Money of(Long price) {
        return new Money(price);
    }
}
