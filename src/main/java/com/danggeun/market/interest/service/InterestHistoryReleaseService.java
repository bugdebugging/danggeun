package com.danggeun.market.interest.service;

import com.danggeun.market.product.domain.Product;
import com.danggeun.market.product.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class InterestHistoryReleaseService {
    private final ProductRepository productRepository;

    public void releaseInterestHistory(Long productId,Long userId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 id의 상품이 존재하지 않습니다.");
                });
        product.removeInterest(userId);
    }
}
