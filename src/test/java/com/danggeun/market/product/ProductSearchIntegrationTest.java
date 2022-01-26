package com.danggeun.market.product;

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
public class ProductSearchIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void 제품_Summaries_조회1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products?size=7&productId=7&categoryId=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(3))
                .andExpect(jsonPath("$.data[0].count_of_reply").value(3))
                .andExpect(jsonPath("$.data[0].count_of_interest").value(3))
                .andExpect(jsonPath("$.data[1].id").value(2))
                .andExpect(jsonPath("$.data[1].count_of_reply").value(1))
                .andExpect(jsonPath("$.data[1].count_of_interest").value(0));
    }
    @Test
    void 제품_Summaries_조회2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products?size=7&productId=5&categoryId=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(4))
                .andExpect(jsonPath("$.data[0].count_of_reply").value(0))
                .andExpect(jsonPath("$.data[0].count_of_interest").value(0))
                .andExpect(jsonPath("$.data[1].id").value(1))
                .andExpect(jsonPath("$.data[1].count_of_reply").value(2))
                .andExpect(jsonPath("$.data[1].count_of_interest").value(1));
    }
    @Test
    void 제품_Summaries_조회3() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products?size=7&productId=3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(2))
                .andExpect(jsonPath("$.data[0].count_of_reply").value(1))
                .andExpect(jsonPath("$.data[0].count_of_interest").value(0))
                .andExpect(jsonPath("$.data[1].id").value(1))
                .andExpect(jsonPath("$.data[1].count_of_reply").value(2))
                .andExpect(jsonPath("$.data[1].count_of_interest").value(1));
    }
}
