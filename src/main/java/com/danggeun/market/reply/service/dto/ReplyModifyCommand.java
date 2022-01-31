package com.danggeun.market.reply.service.dto;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class ReplyModifyCommand {
    private Long productId;
    private Long replyId;
    private String comment;

    public ReplyModifyCommand(Long productId, Long replyId, String comment) {
        checkArgument(productId != null, "상품 id는 필수입니다.");
        checkArgument(replyId != null, "댓글 id는 필수입니다.");
        checkArgument(isNotEmpty(comment), "comment는 필수입니다.");

        this.productId = productId;
        this.replyId = replyId;
        this.comment = comment;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getReplyId() {
        return replyId;
    }

    public String getComment() {
        return comment;
    }
}
