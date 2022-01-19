package com.danggeun.market.interest.service;

import com.danggeun.market.interest.domain.InterestHistory;
import com.danggeun.market.interest.domain.InterestHistoryRepository;
import com.danggeun.market.product.domain.Product;
import com.danggeun.market.product.domain.ProductRepository;
import com.danggeun.market.user.domain.User;
import com.danggeun.market.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class InterestHistoryRegisterService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final InterestHistoryRepository interestHistoryRepository;

    public void giveInterestToProduct(Long userId, Long productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 id의 사용자가 존재하지 않습니다.");
                });
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 id의 상품이 존재하지 않습니다.");
                });

        interestHistoryRepository.findByUserAndProduct(user, product)
                .ifPresent((interestHistory) -> {
                    throw new IllegalStateException("이미 관심을 등록한 상품입니다.");
                });

        InterestHistory interestHistory = new InterestHistory(user, product);
        interestHistoryRepository.save(interestHistory);
    }
}
