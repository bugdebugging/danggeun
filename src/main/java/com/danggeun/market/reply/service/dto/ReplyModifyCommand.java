package com.danggeun.market.reply.service.dto;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class ReplyModifyCommand {
    private Long replyId;
    private String comment;

    public ReplyModifyCommand(Long replyId, String comment) {
        checkArgument(replyId != null, "댓글 id는 필수입니다.");
        checkArgument(isNotEmpty(comment), "comment는 필수입니다.");

        this.replyId = replyId;
        this.comment = comment;
    }

    public Long getReplyId() {
        return replyId;
    }

    public String getComment() {
        return comment;
    }
}
