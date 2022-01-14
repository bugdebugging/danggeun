package com.danggeun.market.product.service;

import com.danggeun.market.product.domain.Product;
import com.danggeun.market.product.domain.ProductRepository;
import com.danggeun.market.product.dto.ProductDetailResponse;
import com.danggeun.market.product.dto.ProductItemResponse;
import com.danggeun.market.product.dto.ProductSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductSearchService {
    private final ProductRepository productRepository;

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
}
