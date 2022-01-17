package com.danggeun.market.reply;

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
    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ReplyResponse postReplyToProduct(ReplyPostCommand replyPostCommand) {
        User user = userRepository.findById(replyPostCommand.getUserId())
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 id의 유저가 존재하지 않습니다.");
                });

        productRepository.findById(replyPostCommand.getProductId())
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 id의 상품이 존재하지 않습니다.");
                });
        Reply reply = new Reply(user, replyPostCommand.getProductId(), replyPostCommand.getComment());
        replyRepository.save(reply);
        return ReplyResponse.fromEntity(reply);
    }
}
