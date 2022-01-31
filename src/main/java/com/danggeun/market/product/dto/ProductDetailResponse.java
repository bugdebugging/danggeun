package com.danggeun.market.product.dto;

import com.danggeun.market.product.domain.Product;
import com.danggeun.market.user.domain.User;
import com.danggeun.market.user.dto.UserSummaryResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDetailResponse {
    private Long id;
    private List<String> productImages;
    private UserSummaryResponse seller;
    private String name;
    private String description;
    private String category;
    private Long price;
    private LocalDateTime createdAt;
    private List<ProductItemResponse> anotherProducts;

    public ProductDetailResponse(User seller, Product product, List<ProductItemResponse> anotherProducts) {
        this.id = product.getId();
        this.productImages = product.getProductImages().stream()
                .map(productImage -> productImage.getImageUrl())
                .collect(Collectors.toList());
        this.seller = UserSummaryResponse.fromEntity(seller);
        this.name = product.getName();
        this.description = product.getDescription();
        this.category = product.getCategory().getName();
        this.price = product.getPrice().getMoney();
        this.createdAt = product.getCreatedAt();
        this.anotherProducts = anotherProducts;
    }

    public Long getId() {
        return id;
    }

    public List<String> getProductImages() {
        return productImages;
    }

    public UserSummaryResponse getSeller() {
        return seller;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public Long getPrice() {
        return price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<ProductItemResponse> getAnotherProducts() {
        return anotherProducts;
    }
}
