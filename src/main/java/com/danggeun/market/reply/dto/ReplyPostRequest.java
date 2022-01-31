package com.danggeun.market.reply.dto;

public class ReplyPostRequest {
    private Long userId;
    private String comment;

    public ReplyPostRequest(Long userId, String comment) {
        this.userId = userId;
        this.comment = comment;
    }

    public Long getUserId() {
        return userId;
    }

    public String getComment() {
        return comment;
    }
}
