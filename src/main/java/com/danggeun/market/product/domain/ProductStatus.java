package com.danggeun.market.product.domain;

public enum ProductStatus {
    SELL("SELL"), RESERVED("RESERVED"), SOLD("SOLD");
    private String value;

    ProductStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
