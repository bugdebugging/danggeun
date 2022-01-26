package com.danggeun.market.product.service.dto;

import com.danggeun.market.product.domain.ProductStatus;
import lombok.Builder;

public class ProductSearchCommand {
    private ProductStatus productStatus;
    private Long categoryId;
    private Long productId;
    private Long sellerId;

    @Builder(builderMethodName = "of")
    public ProductSearchCommand(ProductStatus productStatus, Long categoryId, Long productId, Long sellerId) {
        this.productStatus = productStatus;
        this.categoryId = categoryId;
        this.productId = productId;
        this.sellerId = sellerId;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }


    public Long getCategoryId() {
        return categoryId;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getSellerId() {
        return sellerId;
    }
}
