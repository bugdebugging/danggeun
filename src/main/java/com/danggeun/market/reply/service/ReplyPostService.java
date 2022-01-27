package com.danggeun.market.reply.service;

import com.danggeun.market.product.domain.Product;
import com.danggeun.market.product.domain.ProductRepository;
import com.danggeun.market.reply.domain.Reply;
import com.danggeun.market.reply.domain.ReplyRepository;
import com.danggeun.market.reply.dto.ReplyResponse;
import com.danggeun.market.reply.service.dto.ReplyPostCommand;
import com.danggeun.market.user.domain.User;
import com.danggeun.market.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyPostService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ReplyRepository replyRepository;

    public ReplyResponse postReplyToProduct(ReplyPostCommand replyPostCommand) {
        User user = userRepository.findById(replyPostCommand.getUserId())
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 id의 유저가 존재하지 않습니다.");
                });

        Product product = productRepository.findById(replyPostCommand.getProductId())
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 id의 상품이 존재하지 않습니다.");
                });
        Reply reply = product.addReply(replyPostCommand.getUserId(), replyPostCommand.getComment());
        return new ReplyResponse(replyRepository.save(reply), user);
    }
}
