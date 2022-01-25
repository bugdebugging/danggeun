package com.danggeun.market.product.controller;

import com.danggeun.market.common.api.ApiResult;
import com.danggeun.market.common.api.ApiUtils;
import com.danggeun.market.product.domain.ProductStatus;
import com.danggeun.market.product.service.ProductSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductSearchController {
    private final ProductSearchService productSearchService;

    @GetMapping("/products/{productId}")
    public ApiResult searchProductDetail(@PathVariable(value = "productId") Long productId) {
        return ApiUtils.success(productSearchService.searchProductDetail(productId));
    }

    @GetMapping("/users/{userId}/products")
    public ApiResult searchProductBySeller(@PathVariable("userId") Long userId,
                                           @RequestParam(value = "status", required = false, defaultValue = "SELL") String status,
                                           @RequestParam(value = "size", required = false, defaultValue = "5") int size) {
        return ApiUtils.success(productSearchService.searchProductBySellerAndStatus(userId, ProductStatus.valueOf(status), size));
    }

    @GetMapping("/products")
    public ApiResult searchProducts(@RequestParam(value = "productId", required = false) Long productId,
                                    @RequestParam(value = "categoryId", required = false) Long categoryId,
                                    @RequestParam(value = "size", required = false, defaultValue = "5") int size) {
        return ApiUtils.success(productSearchService.searchProductByCategory(productId, categoryId, size));
    }
}
