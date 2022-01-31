package com.danggeun.market.interest.controller;

import com.danggeun.market.common.api.ApiResult;
import com.danggeun.market.common.api.ApiUtils;
import com.danggeun.market.interest.dto.InterestRegisterRequest;
import com.danggeun.market.interest.service.InterestHistoryRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InterestRegisterController {
    private final InterestHistoryRegisterService interestHistoryRegisterService;

    @PostMapping("/api/products/{productId}/interestHistories")
    public ApiResult registerInterest(@PathVariable("productId") Long productId,
                                      @RequestBody InterestRegisterRequest registerRequest) {
        interestHistoryRegisterService.giveInterestToProduct(productId, registerRequest.getUserId());
        return ApiUtils.success(null);
    }
}
