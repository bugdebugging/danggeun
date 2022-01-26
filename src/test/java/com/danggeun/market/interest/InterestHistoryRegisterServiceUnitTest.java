package com.danggeun.market.interest;

import com.danggeun.market.category.domain.Category;
import com.danggeun.market.interest.domain.InterestHistory;
import com.danggeun.market.interest.domain.InterestHistoryRepository;
import com.danggeun.market.interest.service.InterestHistoryRegisterService;
import com.danggeun.market.product.domain.Money;
import com.danggeun.market.product.domain.Product;
import com.danggeun.market.product.domain.ProductRepository;
import com.danggeun.market.user.domain.User;
import com.danggeun.market.user.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class InterestHistoryRegisterServiceUnitTest {
    @Autowired
    InterestHistoryRegisterService interestHistoryRegisterService;

    @MockBean
    UserRepository userRepository;
    @MockBean
    ProductRepository productRepository;
    @MockBean
    InterestHistoryRepository interestHistoryRepository;

    @Test
    void 이미_관심_등록한경우_예외발생() {
        final Long userId = 1L;
        final Long productId = 1L;
        final User user = new User("email", "password", "name", "phone", "nickname");
        final Product product = new Product(userId, new Category("학용품"), "name", Money.of(10000L), "description", new ArrayList<>());
        final InterestHistory interestHistory = new InterestHistory(user, product);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(interestHistoryRepository.findByUserAndProduct(user, product)).thenReturn(Optional.of(interestHistory));

        assertThrows(IllegalStateException.class, () -> {
            interestHistoryRegisterService.giveInterestToProduct(userId, productId);
        }, "이미 관심 등록한 경우 예외발생.");
    }

    @Test
    void 올바른_관심_등록_성공() {
        final Long userId = 1L;
        final Long productId = 1L;
        final User user = new User("email", "password", "name", "phone", "nickname");
        final Product product = new Product(userId, new Category("학용품"), "name", Money.of(10000L), "description", new ArrayList<>());

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(interestHistoryRepository.findByUserAndProduct(user, product)).thenReturn(Optional.empty());

        interestHistoryRegisterService.giveInterestToProduct(userId, productId);
        verify(interestHistoryRepository, atLeastOnce()).save(any(InterestHistory.class));
    }
}
