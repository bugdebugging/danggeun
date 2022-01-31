package com.danggeun.market.product.service.dto;

import com.danggeun.market.product.domain.Money;
import com.danggeun.market.product.domain.ProductImage;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class ProductPostCommand {
    private Long userId;
    private Long categoryId;
    private String name;
    private Money price;
    private String description;
    private List<ProductImage> productImages;

    public ProductPostCommand(Long userId, Long categoryId, String name, Money price, String description, List<ProductImage> productImages) {
        checkArgument(userId != null, "userId는 필수 입니다.");
        checkArgument(categoryId != null, "categoryId는 필수 입니다.");
        checkArgument(isNotEmpty(name), "userId는 필수 입니다.");
        checkArgument(price != null, "price는 필수입니다.");
        checkArgument(isNotEmpty(description), "description은 필수입니다.");

        this.userId = userId;
        this.categoryId = categoryId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.productImages = productImages;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getCategoryId() {
        return categoryId;
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

    public List<ProductImage> getProductImages() {
        return productImages;
    }
}
