package com.danggeun.market.reply.service.dto;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class ReplyPostCommand {
    private Long userId;
    private Long productId;
    private String comment;

    public ReplyPostCommand(Long userId, Long productId, String comment) {
        checkArgument(userId != null, "사용자 id는 필수입니다.");
        checkArgument(productId != null, "상품 id는 필수입니다.");
        checkArgument(isNotEmpty(comment), "comment는 필수입니다.");

        this.userId = userId;
        this.productId = productId;
        this.comment = comment;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getProductId() {
        return productId;
    }

    public String getComment() {
        return comment;
    }
}
