package com.danggeun.market.interest.controller;

import com.danggeun.market.common.api.ApiResult;
import com.danggeun.market.common.api.ApiUtils;
import com.danggeun.market.interest.service.InterestProductSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InterestHistorySearchController {
    private final InterestProductSearchService interestProductSearchService;

    @GetMapping("/api/users/{userId}/interests")
    public ApiResult searchInterestHistories(@PathVariable("userId") Long userId) {
        return ApiUtils.success(interestProductSearchService.searchInterestProduct(userId));
    }
}
