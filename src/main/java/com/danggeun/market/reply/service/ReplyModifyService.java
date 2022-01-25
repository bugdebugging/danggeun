package com.danggeun.market.reply.service;

import com.danggeun.market.product.domain.Product;
import com.danggeun.market.product.domain.ProductRepository;
import com.danggeun.market.reply.domain.Reply;
import com.danggeun.market.reply.domain.ReplyRepository;
import com.danggeun.market.reply.dto.ReplyResponse;
import com.danggeun.market.reply.service.dto.ReplyModifyCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.google.common.base.Preconditions.checkArgument;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyModifyService {
    private final ProductRepository productRepository;

    public ReplyResponse modifyReply(ReplyModifyCommand replyModifyCommand) {
        Product product = productRepository.findById(replyModifyCommand.getProductId())
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 id의 상품이 존재하지 않습니다.");
                });
        Reply reply = product.changeReplyComment(replyModifyCommand.getReplyId(), replyModifyCommand.getComment());
        return ReplyResponse.fromEntity(reply);
    }

    public void deleteReply(Long productId, Long replyId) {
        checkArgument(productId != null, "상품의 id는 필수입니다.");
        checkArgument(replyId != null, "댓글 id는 필수입니다.");

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 id의 상품이 존재하지 않습니다.");
                });
        product.removeReply(replyId);
    }
}
