package com.danggeun.market.product.dto;

import com.danggeun.market.product.domain.Product;

public class ProductItemResponse {
    private Long id;
    private String thumbnailImage;
    private String name;
    private Long money;

    private ProductItemResponse(Long id, String thumbnailImage, String name, Long money) {
        this.id = id;
        this.thumbnailImage = thumbnailImage;
        this.name = name;
        this.money = money;
    }

    public static ProductItemResponse fromEntity(Product product) {
        return new ProductItemResponse(product.getId(),
                product.getThumbnailImages() != null ? product.getThumbnailImages().getImageUrl() : null,
                product.getName(), product.getPrice().getMoney());
    }

    public Long getId() {
        return id;
    }

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public String getName() {
        return name;
    }

    public Long getMoney() {
        return money;
    }
}
