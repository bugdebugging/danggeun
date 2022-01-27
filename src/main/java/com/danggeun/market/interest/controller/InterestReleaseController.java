package com.danggeun.market.interest.controller;

import com.danggeun.market.common.api.ApiResult;
import com.danggeun.market.common.api.ApiUtils;
import com.danggeun.market.interest.dto.InterestReleaseRequest;
import com.danggeun.market.interest.service.InterestHistoryReleaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InterestReleaseController {
    private final InterestHistoryReleaseService interestHistoryReleaseService;

    @DeleteMapping("/api/products/{productId}/interestHistories")
    public ApiResult releaseInterest(@PathVariable("productId") Long productId,
                                     @RequestBody InterestReleaseRequest releaseRequest) {
        interestHistoryReleaseService.releaseInterestHistory(productId, releaseRequest.getUserId());
        return ApiUtils.success(null);
    }
}
