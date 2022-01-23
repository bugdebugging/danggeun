package com.danggeun.market.product.dto;

public class ProductPostRequest {
    private Long userId;
    private Long categoryId;
    private String name;
    private Long price;
    private String description;

    public ProductPostRequest() {
    }

    public ProductPostRequest(Long userId, Long categoryId, String name, Long price, String description) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
