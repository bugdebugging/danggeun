package com.danggeun.market.reply.controller;

import com.danggeun.market.common.api.ApiResult;
import com.danggeun.market.common.api.ApiUtils;
import com.danggeun.market.reply.dto.ReplyPostRequest;
import com.danggeun.market.reply.dto.ReplyResponse;
import com.danggeun.market.reply.service.ReplyPostService;
import com.danggeun.market.reply.service.dto.ReplyPostCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReplyPostController {
    private final ReplyPostService replyPostService;

    @PostMapping("/products/{productId}/replies")
    public ApiResult postReply(@PathVariable("productId") Long productId,
                               @RequestBody ReplyPostRequest replyPostRequest) {
        ReplyPostCommand replyPostCommand = new ReplyPostCommand(replyPostRequest.getUserId(), productId, replyPostRequest.getComment());
        ReplyResponse replyResponse = replyPostService.postReplyToProduct(replyPostCommand);
        return ApiUtils.success(replyResponse);
    }
}
