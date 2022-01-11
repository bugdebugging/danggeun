package com.danggeun.market.interest.domain;

import com.danggeun.market.product.domain.Product;
import com.danggeun.market.user.domain.User;

import javax.persistence.*;

@Entity
@Table(name="interest_product_histories")
public class InterestHistory {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    protected InterestHistory() {
    }

    public InterestHistory(User user, Product product) {
        this.user = user;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }
}
