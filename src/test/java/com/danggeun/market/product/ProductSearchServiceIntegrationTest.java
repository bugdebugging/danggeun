package com.danggeun.market.product;

import com.danggeun.market.product.dto.ProductDetailResponse;
import com.danggeun.market.product.service.ProductSearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductSearchServiceIntegrationTest {
    @Autowired
    ProductSearchService productSearchService;


    @Test
    void Product_상세조회() {
        final Long productId = 1L;
        ProductDetailResponse result = productSearchService.searchProductDetail(productId);

        assertEquals(2, result.getAnotherProducts().size(), "해당 상품 판매자의 다른 상품 조회 결과개수가 2개");
        assertEquals(2, result.getAnotherProducts().get(0).getId(), "판매자의 다른 상품 첫번째는 2");
        assertEquals(3, result.getAnotherProducts().get(1).getId(), "판매자의 다른 상품 두번째는 3");

        assertEquals(1, result.getId());
        assertEquals(2, result.getProductImages().size(), "해당 제품의 이미지 개수는 2개");

        assertEquals(58000L, result.getPrice());

        assertEquals(1, result.getSeller().getId());
        assertEquals(null, result.getSeller().getProfile());
        assertEquals("mynickname", result.getSeller().getNickname());
    }
}
