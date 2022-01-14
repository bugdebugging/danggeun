package com.danggeun.market.reply;

import com.danggeun.market.reply.dto.ReplyResponse;
import com.danggeun.market.reply.service.ReplySearchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ReplySearchIntegrationTest {
    @Autowired
    ReplySearchService replySearchService;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void 상품의댓글_조회() throws JsonProcessingException {
        final Long productId = 3L;
        final int page = 0;
        final int size = 2;

        List<ReplyResponse> result = replySearchService.searchReplies(productId, page, size);

        assertEquals(2, result.size(), "3번 상품의 댓글은 3개");

        assertEquals(6, result.get(0).getId());
        assertEquals(5, result.get(1).getId());

        assertEquals(2, result.get(0).getUserSummaryResponse().getId());
        assertEquals(2, result.get(1).getUserSummaryResponse().getId());

        System.out.println(objectMapper.writeValueAsString(result));
    }
}
