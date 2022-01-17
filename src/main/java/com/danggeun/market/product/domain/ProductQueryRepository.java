package com.danggeun.market.product.domain;

import com.danggeun.market.product.dto.ProductSummaryResponse;
import com.danggeun.market.product.service.dto.ProductSearchCommand;

import java.util.List;

public interface ProductQueryRepository {
    List<ProductSummaryResponse> findProductSummaries(ProductSearchCommand command, int size);
}
