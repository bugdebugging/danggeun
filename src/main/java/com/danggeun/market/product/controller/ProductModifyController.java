package com.danggeun.market.product.controller;

import com.danggeun.market.common.api.ApiResult;
import com.danggeun.market.common.api.ApiUtils;
import com.danggeun.market.product.dto.ProductStatusModifyRequest;
import com.danggeun.market.product.service.ProductStatusModifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductModifyController {
    private final ProductStatusModifyService productStatusModifyService;

    @PutMapping("/api/products/{productId}")
    public ApiResult changeProductStatus(@PathVariable("productId") Long productId,
                                         @RequestBody ProductStatusModifyRequest productStatusModifyRequest) {
        productStatusModifyService.changeStatus(productId, productStatusModifyRequest.getStatus());
        return ApiUtils.success(null);
    }
}
