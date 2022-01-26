package com.danggeun.market.product;

import com.danggeun.market.category.domain.Category;
import com.danggeun.market.category.domain.CategoryRepository;
import com.danggeun.market.product.domain.*;
import com.danggeun.market.product.dto.ProductSummaryResponse;
import com.danggeun.market.product.service.dto.ProductSearchCommand;
import com.danggeun.market.user.domain.User;
import com.danggeun.market.user.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void Product_저장() {
        final Long userId = 1L;
        final String name = "조던 신발";
        final Money price = Money.of(700000L);
        final String description = "한번도 안신은 조던신발";
        final int countOfImages = 3;

        List<ProductImage> uploadedProductImages = new ArrayList<>();
        for (int i = 0; i < countOfImages; i++) {
            uploadedProductImages.add(ProductImage.of("https://s3.ap-northeast-2.amazonaws.com/danggeun_product_imgs" + i));
        }

        Category category = categoryRepository.findById(1L).get();

        Product product = new Product(userId, category, name, price, description, uploadedProductImages);
        Product savedProduct = productRepository.save(product);

        assertNotNull(savedProduct.getId());
        assertEquals(name, savedProduct.getName());
        assertEquals(price, savedProduct.getPrice());
        assertEquals(description, savedProduct.getDescription());
        assertEquals(userId, savedProduct.getSellerId());
        assertEquals(ProductStatus.SELL, savedProduct.getStatus());
        for (int i = 0; i < countOfImages; i++) {
            assertEquals(uploadedProductImages.get(i), product.getProductImages().get(i));
        }
    }

    @Test
    void Product_Summary_조회() {
        ProductSearchCommand command = ProductSearchCommand.of()
                .productId(4L)
                .build();
        List<ProductSummaryResponse> productSummaries = productRepository.findProductSummaries(command, 4);
        //3
        assertEquals(3, productSummaries.get(0).getCountOfReply());
        assertEquals(3, productSummaries.get(0).getCountOfInterest());

        //2
        assertEquals(1, productSummaries.get(1).getCountOfReply());
        assertEquals(0, productSummaries.get(1).getCountOfInterest());

        //1
        assertEquals(2, productSummaries.get(2).getCountOfReply());
        assertEquals(1, productSummaries.get(2).getCountOfInterest());
    }

    @Test
    void 판매자가_판매중인_Product_Summary_조회() {
        User user = userRepository.findById(2L).get();

        ProductSearchCommand command = ProductSearchCommand.of()
                .productStatus(ProductStatus.SELL)
                .seller(user)
                .build();
        List<ProductSummaryResponse> productSummaries = productRepository.findProductSummaries(command, 10);
        assertEquals(2, productSummaries.size());

        int notSellStatusProductCount = productSummaries.stream()
                .filter(productSummaryResponse -> !productSummaryResponse.getStatus().equals(ProductStatus.SELL))
                .collect(Collectors.toList()).size();
        assertEquals(0, notSellStatusProductCount, "판매중인 상품만 뽑힌다.");
    }

    @Test
    void 판매자가_거래완료한_Product_Summary_조회() {
        User user = userRepository.findById(2L).get();

        ProductSearchCommand command = ProductSearchCommand.of()
                .productStatus(ProductStatus.SOLD)
                .seller(user)
                .build();
        List<ProductSummaryResponse> productSummaries = productRepository.findProductSummaries(command, 10);
        assertEquals(2, productSummaries.size());

        int soldOutStatusProductCount = productSummaries.stream()
                .filter(productSummaryResponse -> !productSummaryResponse.getStatus().equals(ProductStatus.SOLD))
                .collect(Collectors.toList()).size();
        assertEquals(0, soldOutStatusProductCount, "판매완료한 상품만 뽑힌다.");
    }

    @Test
    void 카테고리별_Product_Summary_조회() {
        final Long categoryId = 1L;
        ProductSearchCommand command = ProductSearchCommand.of()
                .categoryId(categoryId)
                .productId(5L)
                .build();
        List<ProductSummaryResponse> productSummaries = productRepository.findProductSummaries(command, 10);
        assertEquals(2, productSummaries.size());

        assertEquals(4, productSummaries.get(0).getId());
        assertEquals(1, productSummaries.get(1).getId());
    }
}
