package com.danggeun.market.product.dto;

import com.danggeun.market.product.domain.Product;
import com.danggeun.market.product.domain.ProductStatus;

public class ProductSummaryResponse {
    private Long id;
    private String name;
    private Long price;
    private String thumbnailImageUrl;
    private ProductStatus status;
    private Integer countOfReply;
    private Long countOfInterest;

    public ProductSummaryResponse(Product product, Integer countOfReply, Long countOfInterest) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice().getMoney();
        if (product.getThumbnailImages() != null)
            this.thumbnailImageUrl = product.getThumbnailImages().getImageUrl();
        this.status = product.getStatus();
        this.countOfReply = countOfReply;
        this.countOfInterest = countOfInterest;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }

    public String getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public Integer getCountOfReply() {
        return countOfReply;
    }

    public Long getCountOfInterest() {
        return countOfInterest;
    }
}
