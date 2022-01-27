package com.danggeun.market.interest.domain;

import com.danggeun.market.product.domain.Product;
import com.danggeun.market.user.domain.User;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="interest_product_histories")
public class InterestHistory {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    protected InterestHistory() {
    }

    public InterestHistory(Long userId, Product product) {
        this.userId = userId;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InterestHistory that = (InterestHistory) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
