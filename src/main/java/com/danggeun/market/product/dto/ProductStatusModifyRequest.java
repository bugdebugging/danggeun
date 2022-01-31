package com.danggeun.market.product.dto;

import com.danggeun.market.product.domain.ProductStatus;

public class ProductStatusModifyRequest {
    private ProductStatus status;

    public ProductStatusModifyRequest() {
    }

    public ProductStatus getStatus() {
        return status;
    }
}
