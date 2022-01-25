package com.danggeun.market.reply.domain;

import com.danggeun.market.product.domain.Product;
import com.danggeun.market.user.domain.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

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

    public Reply(User writer, Product product, String comment) {
        this.writer = writer;
        this.product = product;
        this.comment = comment;
    }

    public void changeContent(String comment) {
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public User getWriter() {
        return writer;
    }

    public Product getProduct() {
        return product;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reply reply = (Reply) o;
        return Objects.equals(id, reply.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
