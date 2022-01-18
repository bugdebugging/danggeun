package com.danggeun.market.interest;

import com.danggeun.market.interest.service.InterestProductSearchService;
import com.danggeun.market.product.dto.ProductSummaryResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class InterestProductSearchServiceIntegrationTest {
    @Autowired
    InterestProductSearchService interestProductSearchService;

    @Test
    void 관심가지는_ProductSummary조회() {
        List<ProductSummaryResponse> result = interestProductSearchService.searchInterestProduct(2L);

        assertEquals(2, result.size());
        //1
        assertEquals(2, result.get(0).getCountOfReply(), "id가 1인 상품의 댓글의 개수는 2");
        assertEquals(1, result.get(0).getCountOfInterest(), "id가 1인 상품의 관심의 개수는 2");

        assertEquals(3, result.get(1).getCountOfReply(), "id가 3인 상품의 댓글의 개수는 3");
        assertEquals(3, result.get(1).getCountOfInterest(), "id가 3인 상품의 관심의 개수는 3");
    }
}
