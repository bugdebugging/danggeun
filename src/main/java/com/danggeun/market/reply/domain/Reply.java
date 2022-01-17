package com.danggeun.market.reply.domain;

import com.danggeun.market.user.domain.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "replies")
public class Reply {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User writer;

    @Column
    private Long productId;

    @Column
    private String comment;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    protected Reply() {
    }

    public Reply(User writer, Long productId, String comment) {
        this.writer = writer;
        this.productId = productId;
        this.comment = comment;
    }

    public void changeContent(Long userId, String comment) {
        if (!userId.equals(writer.getId())) {
            throw new IllegalArgumentException("댓글의 소유자만 지울 수 있습니다.");
        }
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public User getWriter() {
        return writer;
    }

    public Long getProductId() {
        return productId;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
