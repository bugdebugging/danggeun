package com.danggeun.market.product.domain;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@EqualsAndHashCode
public class ProductImage {
    @Column(name = "image_url")
    private String imageUrl;

    protected ProductImage() {
    }

    private ProductImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static ProductImage of(String imageUrl) {
        return new ProductImage(imageUrl);
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
