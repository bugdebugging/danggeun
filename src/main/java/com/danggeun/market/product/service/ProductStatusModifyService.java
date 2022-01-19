package com.danggeun.market.product.service;

import com.danggeun.market.product.domain.Product;
import com.danggeun.market.product.domain.ProductRepository;
import com.danggeun.market.product.domain.ProductStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.google.common.base.Preconditions.checkArgument;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductStatusModifyService {
    private final ProductRepository productRepository;

    public void changeStatus(Long productId, ProductStatus status) {
        checkArgument(productId != null, "변경할 상품의 id는 필수입니다.");
        checkArgument(status != null, "상품의 목표 상태는 필수입니다.");
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 id의 상품이 존재하지않습니다.");
                });
        product.changeStatus(status);
    }
}
