package com.danggeun.market.product.service.dto;

import com.danggeun.market.category.domain.Category;
import com.danggeun.market.product.domain.ProductStatus;
import com.danggeun.market.user.domain.User;
import lombok.Builder;

public class ProductSearchCommand {
    private ProductStatus productStatus;
    private Category category;
    private Long productId;
    private User seller;

    @Builder(builderMethodName = "of")
    public ProductSearchCommand(ProductStatus productStatus, Category category, Long productId, User seller) {
        this.productStatus = productStatus;
        this.category = category;
        this.productId = productId;
        this.seller = seller;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public Category getCategory() {
        return category;
    }

    public Long getProductId() {
        return productId;
    }

    public User getSeller() {
        return seller;
    }
}
