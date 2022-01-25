package com.danggeun.market.product.service;

import com.danggeun.market.product.domain.Product;
import com.danggeun.market.product.domain.ProductRepository;
import com.danggeun.market.product.domain.ProductStatus;
import com.danggeun.market.product.dto.ProductDetailResponse;
import com.danggeun.market.product.dto.ProductItemResponse;
import com.danggeun.market.product.dto.ProductSummaryResponse;
import com.danggeun.market.product.service.dto.ProductSearchCommand;
import com.danggeun.market.user.domain.User;
import com.danggeun.market.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductSearchService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductDetailResponse searchProductDetail(Long productId) {
        Product product = productRepository.findProductByIdWithProductImagesAndSellerAndCategory(productId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 id와 동일한 상품이 존재하지 않습니다.");
                });

        List<ProductItemResponse> productItemResponses = productRepository.findProductBySeller(product.getSeller())
                .stream()
                .filter(anotherProduct -> anotherProduct.getId() != productId)
                .map(productBelongingSeller -> ProductItemResponse.fromEntity(productBelongingSeller))
                .collect(Collectors.toList());

        return new ProductDetailResponse(product, productItemResponses);
    }

    public List<ProductSummaryResponse> searchProductBySellerAndStatus(Long userId, ProductStatus status, int size) {
        checkArgument(userId != null, "사용자의 id는 필수입니다.");
        checkArgument(status != null, "상품의 상태는 필수입니다.");

        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 id와 일치하는 사용자가 존재하지 않습니다.");
                });
        ProductSearchCommand command = ProductSearchCommand.of()
                .seller(user)
                .productStatus(status)
                .build();
        return productRepository.findProductSummaries(command, size);
    }

    public List<ProductSummaryResponse> searchProductByCategory(Long productId, Long categoryId, int size) {
        ProductSearchCommand command = ProductSearchCommand.of()
                .categoryId(categoryId)
                .productId(productId)
                .build();

        return productRepository.findProductSummaries(command, size);
    }
}
