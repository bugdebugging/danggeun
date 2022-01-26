package com.danggeun.market.reply;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ReplySearchIntegrationTest {
    @Autowired
    MockMvc mvc;

    @Test
    void 댓글조회1() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/products/1/replies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(2))
                .andExpect(jsonPath("$.data[0].user_summary_response.id").value(2))
                .andExpect(jsonPath("$.data[1].id").value(1))
                .andExpect(jsonPath("$.data[1].user_summary_response.id").value(2));
    }

    @Test
    void 댓글조회2() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/products/2/replies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(3))
                .andExpect(jsonPath("$.data[0].user_summary_response.id").value(2));
    }
}
