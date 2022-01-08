package com.danggeun.market.product.domain;

import com.danggeun.market.user.domain.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column
    private String name;

    @Embedded
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

    protected Product() {
    }

    public Product(User seller, String name, Money price, String description) {
        this.seller = seller;
        this.name = name;
        this.price = price;
        this.description = description;
        this.status=ProductStatus.SELL;
    }

    public Long getId() {
        return id;
    }

    public User getSeller() {
        return seller;
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
}
