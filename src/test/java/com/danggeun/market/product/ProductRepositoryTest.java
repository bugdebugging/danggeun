package com.danggeun.market.product;

import com.danggeun.market.product.domain.Money;
import com.danggeun.market.product.domain.Product;
import com.danggeun.market.product.domain.ProductRepository;
import com.danggeun.market.product.domain.ProductStatus;
import com.danggeun.market.user.domain.User;
import com.danggeun.market.user.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void Product_저장() {
        final String name = "조던 신발";
        final Money price = Money.of(700000L);
        final String description = "한번도 안신은 조던신발";

        User user = userRepository.findById(1L).get();
        Product product = new Product(user, name, price, description);
        Product savedProduct = productRepository.save(product);

        assertNotNull(savedProduct.getId());
        assertEquals(name, savedProduct.getName());
        assertEquals(price, savedProduct.getPrice());
        assertEquals(description, savedProduct.getDescription());
        assertEquals(user.getId(), savedProduct.getSeller().getId());
        assertEquals(ProductStatus.SELL, savedProduct.getStatus());
    }
}
