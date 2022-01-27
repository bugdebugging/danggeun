package com.danggeun.market.interest;

import com.danggeun.market.category.domain.Category;
import com.danggeun.market.interest.domain.InterestHistoryRepository;
import com.danggeun.market.interest.service.InterestHistoryRegisterService;
import com.danggeun.market.product.domain.Money;
import com.danggeun.market.product.domain.Product;
import com.danggeun.market.product.domain.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class InterestHistoryRegisterServiceUnitTest {
    @Autowired
    InterestHistoryRegisterService interestHistoryRegisterService;

    @MockBean
    ProductRepository productRepository;
    @MockBean
    InterestHistoryRepository interestHistoryRepository;

    @Test
    void 이미_관심_등록한경우_예외발생() {
        final Long userId = 1L;
        final Long productId = 1L;
        final Product product = new Product(userId, new Category("학용품"), "name", Money.of(10000L), "description", new ArrayList<>());
        product.addInterest(userId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        assertThrows(IllegalStateException.class, () -> {
            interestHistoryRegisterService.giveInterestToProduct(userId, productId);
        }, "이미 관심 등록한 경우 예외발생.");
    }

    @Test
    void 올바른_관심_등록_성공() {
        final Long userId = 1L;
        final Long productId = 1L;
        final Product product = new Product(userId, new Category("학용품"), "name", Money.of(10000L), "description", new ArrayList<>());

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        interestHistoryRegisterService.giveInterestToProduct(userId, productId);
        int result = product.getInterestHistories().stream()
                .filter(interestHistory -> interestHistory.getUserId().equals(userId))
                .collect(Collectors.toList()).size();
        assertEquals(1, result);
    }
}
