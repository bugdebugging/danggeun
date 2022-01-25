package com.danggeun.market.product.service.dto;

import com.danggeun.market.product.domain.ProductStatus;
import com.danggeun.market.user.domain.User;
import lombok.Builder;

public class ProductSearchCommand {
    private ProductStatus productStatus;
    private Long categoryId;
    private Long productId;
    private User seller;

    @Builder(builderMethodName = "of")
    public ProductSearchCommand(ProductStatus productStatus, Long categoryId, Long productId, User seller) {
        this.productStatus = productStatus;
        this.categoryId = categoryId;
        this.productId = productId;
        this.seller = seller;
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

    public User getSeller() {
        return seller;
    }
}
