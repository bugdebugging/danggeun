package com.danggeun.market.product.domain;

import com.danggeun.market.category.domain.Category;
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
}
