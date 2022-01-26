package com.danggeun.market.product.domain;

import com.danggeun.market.category.domain.Category;
import com.danggeun.market.interest.domain.InterestHistory;
import com.danggeun.market.reply.domain.Reply;
import com.danggeun.market.user.domain.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User seller;

    @JoinColumn(name = "category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @Column
    private String name;

    @Embedded
    @AttributeOverride(name = "money", column = @Column(name = "price"))
    private Money price;

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    private List<ProductImage> productImages = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replies = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InterestHistory> interestHistories = new ArrayList<>();

    @Embedded
    @AttributeOverride(name = "imageUrl", column = @Column(name = "thumbnail_image_url"))
    private ProductImage thumbnailImages;

    protected Product() {
    }

    public Product(User seller, Category category, String name, Money price, String description, List<ProductImage> productImages) {
        this.seller = seller;
        this.category = category;
        this.name = name;
        this.price = price;
        this.description = description;
        this.status = ProductStatus.SELL;
        this.productImages = productImages;
        if (!productImages.isEmpty()) {
            thumbnailImages = productImages.get(0);
        }
    }

    public Reply addReply(User writer, String comment) {
        Reply reply = new Reply(writer, this, comment);
        this.replies.add(reply);
        return reply;
    }

    public void removeReply(Long replyId) {
        Reply targetReply = this.replies.stream()
                .filter(reply -> reply.getId().equals(replyId))
                .findFirst().orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 id의 댓글이 존재하지 않습니다.");
                });
        replies.remove(targetReply);
    }

    public Reply changeReplyComment(Long replyId, String comment) {
        Reply targetReply = this.replies.stream()
                .filter(reply -> reply.getId().equals(replyId))
                .findFirst().orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 id의 댓글이 존재하지 않습니다.");
                });
        targetReply.changeContent(comment);
        return targetReply;
    }

    public InterestHistory addInterest(User user) {
        InterestHistory interestHistory = new InterestHistory(user, this);
        this.interestHistories.add(interestHistory);
        return interestHistory;
    }

    public void removeInterest(Long interestHistoryId) {
        InterestHistory targetInterestHistory = this.interestHistories.stream()
                .filter(interestHistory -> interestHistory.getId().equals(interestHistoryId))
                .findFirst().orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 id의 관심이 존재하지 않습니다.");
                });
        this.interestHistories.remove(targetInterestHistory);
    }

    public void changeStatus(ProductStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public User getSeller() {
        return seller;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public ProductImage getThumbnailImages() {
        return thumbnailImages;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public List<InterestHistory> getInterestHistories() {
        return interestHistories;
    }
}
