package com.danggeun.market.reply.service.dto;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class ReplyModifyCommand {
    private Long userId;
    private Long replyId;
    private String comment;

    public ReplyModifyCommand(Long userId, Long replyId, String comment) {
        checkArgument(userId != null, "사용자 id는 필수입니다.");
        checkArgument(replyId != null, "댓글 id는 필수입니다.");
        checkArgument(isNotEmpty(comment), "comment는 필수입니다.");

        this.userId = userId;
        this.replyId = replyId;
        this.comment = comment;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getReplyId() {
        return replyId;
    }

    public String getComment() {
        return comment;
    }
}
