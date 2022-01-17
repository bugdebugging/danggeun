package com.danggeun.market.reply.service;

import com.danggeun.market.reply.domain.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyModifyService {
    private final ReplyRepository replyRepository;
}
