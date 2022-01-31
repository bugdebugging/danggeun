package com.danggeun.market.reply.controller;

import com.danggeun.market.common.api.ApiResult;
import com.danggeun.market.common.api.ApiUtils;
import com.danggeun.market.reply.dto.ReplyResponse;
import com.danggeun.market.reply.service.ReplySearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReplySearchController {
    private final ReplySearchService replySearchService;

    @GetMapping("/products/{productId}/replies")
    public ApiResult searchReplyPostedProduct(@PathVariable("productId") Long productId,
                                              @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                                              @RequestParam(value = "page", required = false, defaultValue = "0") int page) {
        List<ReplyResponse> replyResponses = replySearchService.searchReplies(productId, page, size);
        return ApiUtils.success(replyResponses);
    }
}
