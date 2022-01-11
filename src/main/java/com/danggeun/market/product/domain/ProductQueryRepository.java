package com.danggeun.market.product.domain;

import com.danggeun.market.product.dto.ProductSummaryResponse;

import java.util.List;

public interface ProductQueryRepository {
    List<ProductSummaryResponse> findProductSummaries(Long productId, int size);
}
