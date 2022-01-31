package com.danggeun.market.reply.service;

import com.danggeun.market.reply.domain.Reply;
import com.danggeun.market.reply.domain.ReplyRepository;
import com.danggeun.market.reply.dto.ReplyResponse;
import com.danggeun.market.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplySearchService {
    private final ReplyRepository replyRepository;

    public List<ReplyResponse> searchReplies(Long productId, int page, int size) {
        return replyRepository.findRepliesWithWriterTest(productId, PageRequest.of(page, size))
                .stream().map(objects -> new ReplyResponse((Reply) objects[0], (User) objects[1]))
                .collect(Collectors.toList());

    }
}
