package com.danggeun.market.product.service;

import com.danggeun.market.category.domain.Category;
import com.danggeun.market.category.domain.CategoryRepository;
import com.danggeun.market.product.domain.Product;
import com.danggeun.market.product.domain.ProductRepository;
import com.danggeun.market.product.service.dto.ProductPostCommand;
import com.danggeun.market.user.domain.User;
import com.danggeun.market.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductPostService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public Long postProduct(ProductPostCommand productPostCommand) {
        User user = userRepository.findById(productPostCommand.getUserId())
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 id의 사용자가 존재하지 않습니다.");
                });
        Category category = categoryRepository.findById(productPostCommand.getCategoryId())
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 id의 카테고리가 존재하지 않습니다.");
                });

        Product product = new Product(user, category,
                productPostCommand.getName(),
                productPostCommand.getPrice(),
                productPostCommand.getDescription(),
                productPostCommand.getProductImages());

        return productRepository.save(product).getId();
    }
}
