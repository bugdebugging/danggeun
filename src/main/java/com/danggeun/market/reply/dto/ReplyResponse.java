package com.danggeun.market.reply.dto;

import com.danggeun.market.reply.domain.Reply;
import com.danggeun.market.user.dto.UserSummaryResponse;

import java.time.LocalDateTime;

public class ReplyResponse {
    private Long id;
    private UserSummaryResponse userSummaryResponse;
    private String comment;
    private LocalDateTime createdAt;

    private ReplyResponse(Long id, UserSummaryResponse userSummaryResponse, String comment, LocalDateTime createdAt) {
        this.id = id;
        this.userSummaryResponse = userSummaryResponse;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public static ReplyResponse fromEntity(Reply reply) {
        return new ReplyResponse(reply.getId(),
                UserSummaryResponse.fromEntity(reply.getWriter()),
                reply.getComment(),
                reply.getCreatedAt());
    }

    public Long getId() {
        return id;
    }

    public UserSummaryResponse getUserSummaryResponse() {
        return userSummaryResponse;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
