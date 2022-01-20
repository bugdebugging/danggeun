package com.danggeun.market.product;

import com.danggeun.market.category.domain.Category;
import com.danggeun.market.category.domain.CategoryRepository;
import com.danggeun.market.product.domain.*;
import com.danggeun.market.user.domain.User;
import com.danggeun.market.user.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void Product_저장() {
        final String name = "조던 신발";
        final Money price = Money.of(700000L);
        final String description = "한번도 안신은 조던신발";
        final int countOfImages = 3;

        List<ProductImage> uploadedProductImages = new ArrayList<>();
        for (int i = 0; i < countOfImages; i++) {
            uploadedProductImages.add(ProductImage.of("https://s3.ap-northeast-2.amazonaws.com/danggeun_product_imgs" + i));
        }

        User user = userRepository.findById(1L).get();
        Category category = categoryRepository.findById(1L).get();

        Product product = new Product(user, category, name, price, description, uploadedProductImages);
        Product savedProduct = productRepository.save(product);

        assertNotNull(savedProduct.getId());
        assertEquals(name, savedProduct.getName());
        assertEquals(price, savedProduct.getPrice());
        assertEquals(description, savedProduct.getDescription());
        assertEquals(user.getId(), savedProduct.getSeller().getId());
        assertEquals(ProductStatus.SELL, savedProduct.getStatus());
        for (int i = 0; i < countOfImages; i++) {
            assertEquals(uploadedProductImages.get(i), product.getProductImages().get(i));
        }
    }
}
