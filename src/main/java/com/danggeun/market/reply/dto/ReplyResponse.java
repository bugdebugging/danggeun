package com.danggeun.market.reply.dto;

import com.danggeun.market.reply.domain.Reply;
import com.danggeun.market.user.domain.User;
import com.danggeun.market.user.dto.UserSummaryResponse;

import java.time.LocalDateTime;

public class ReplyResponse {
    private Long id;
    private String comment;
    private LocalDateTime createdAt;
    private UserSummaryResponse userSummaryResponse;

    public ReplyResponse(Reply reply, User user) {
        this.id = reply.getId();
        this.comment = reply.getComment();
        this.createdAt = reply.getCreatedAt();
        this.userSummaryResponse = UserSummaryResponse.fromEntity(user);
    }

    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public UserSummaryResponse getUserSummaryResponse() {
        return userSummaryResponse;
    }
}
