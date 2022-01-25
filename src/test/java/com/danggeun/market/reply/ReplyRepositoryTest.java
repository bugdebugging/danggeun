package com.danggeun.market.reply;

import com.danggeun.market.product.domain.Product;
import com.danggeun.market.product.domain.ProductRepository;
import com.danggeun.market.reply.domain.Reply;
import com.danggeun.market.reply.domain.ReplyRepository;
import com.danggeun.market.user.domain.User;
import com.danggeun.market.user.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ReplyRepositoryTest {
    @Autowired
    ReplyRepository replyRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    @Test
    void reply_저장() {
        final Long userId = 1L;
        final Long productId = 1L;
        final String comments = "너무 비싸네요.";

        User user = userRepository.findById(userId).get();
        Product product = productRepository.findById(productId).get();

        Reply savedReply = product.addReply(user, comments);
        productRepository.save(product);

        assertEquals(comments, savedReply.getComment());
    }
}
