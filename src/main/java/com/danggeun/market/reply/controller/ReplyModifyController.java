package com.danggeun.market.reply.controller;

import com.danggeun.market.common.api.ApiResult;
import com.danggeun.market.common.api.ApiUtils;
import com.danggeun.market.reply.dto.ReplyModifyRequest;
import com.danggeun.market.reply.service.ReplyModifyService;
import com.danggeun.market.reply.service.dto.ReplyModifyCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReplyModifyController {
    private final ReplyModifyService replyModifyService;

    @PutMapping("/products/{productId}/replies/{replyId}")
    public ApiResult modifyReply(@PathVariable("productId") Long productId,
                                 @PathVariable("replyId") Long replyId,
                                 @RequestBody ReplyModifyRequest replyModifyRequest) {
        ReplyModifyCommand replyModifyCommand = new ReplyModifyCommand(productId, replyId, replyModifyRequest.getComment());
        replyModifyService.modifyReply(replyModifyCommand);
        return ApiUtils.success(null);
    }

    @DeleteMapping("/products/{productId}/replies/{replyId}")
    public ApiResult deleteReply(@PathVariable("productId") Long productId,
                                 @PathVariable("replyId") Long replyId) {
        replyModifyService.deleteReply(productId, replyId);
        return ApiUtils.success(null);
    }
}
