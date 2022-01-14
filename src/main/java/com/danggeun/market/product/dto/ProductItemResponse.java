package com.danggeun.market.product.dto;

import com.danggeun.market.product.domain.Product;
import com.danggeun.market.product.domain.ProductImage;

public class ProductItemResponse {
    private Long id;
    private ProductImage thumbnailImage;
    private String name;
    private Long money;

    private ProductItemResponse(Long id, ProductImage thumbnailImage, String name, Long money) {
        this.id = id;
        this.thumbnailImage = thumbnailImage;
        this.name = name;
        this.money = money;
    }

    public static ProductItemResponse fromEntity(Product product) {
        return new ProductItemResponse(product.getId(), product.getThumbnailImages(),
                product.getName(), product.getPrice().getMoney());
    }

    public Long getId() {
        return id;
    }

    public ProductImage getThumbnailImage() {
        return thumbnailImage;
    }

    public String getName() {
        return name;
    }

    public Long getMoney() {
        return money;
    }
}
