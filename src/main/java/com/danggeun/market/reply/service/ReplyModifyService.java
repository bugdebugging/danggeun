package com.danggeun.market.reply.service;

import com.danggeun.market.reply.domain.Reply;
import com.danggeun.market.reply.domain.ReplyRepository;
import com.danggeun.market.reply.dto.ReplyResponse;
import com.danggeun.market.reply.service.dto.ReplyModifyCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyModifyService {
    private final ReplyRepository replyRepository;

    public ReplyResponse modifyReply(ReplyModifyCommand replyModifyCommand) {
        Reply reply = replyRepository.findByIdWithWriter(replyModifyCommand.getReplyId())
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 id의 댓글이 존재하지 않습니다.");
                });
        reply.changeContent(replyModifyCommand.getUserId(), replyModifyCommand.getComment());

        return ReplyResponse.fromEntity(reply);
    }
}
