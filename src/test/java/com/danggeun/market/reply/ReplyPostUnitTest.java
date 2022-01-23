package com.danggeun.market.reply;

import com.danggeun.market.category.domain.Category;
import com.danggeun.market.product.domain.Money;
import com.danggeun.market.product.domain.Product;
import com.danggeun.market.product.domain.ProductRepository;
import com.danggeun.market.reply.domain.Reply;
import com.danggeun.market.reply.domain.ReplyRepository;
import com.danggeun.market.reply.service.ReplyPostService;
import com.danggeun.market.reply.service.dto.ReplyPostCommand;
import com.danggeun.market.user.domain.User;
import com.danggeun.market.user.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class ReplyPostUnitTest {
    @Autowired
    ReplyPostService replyPostService;

    @MockBean
    ReplyRepository replyRepository;

    @MockBean
    UserRepository userRepository;

    @MockBean
    ProductRepository productRepository;

    @Test
    void 정상적인_댓글_등록() {
        final Category category=new Category("의류");
        final User user = new User("email", "pw", "name", "phone", "nickname");
        final Product product=new Product(user,category,"패딩", Money.of(14000L),"패딩",new ArrayList<>());

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        ReplyPostCommand replyPostCommand=new ReplyPostCommand(1L,1L,"this is reply test");
        replyPostService.postReplyToProduct(replyPostCommand);

        verify(replyRepository, atLeastOnce()).save(any(Reply.class));
    }
}
