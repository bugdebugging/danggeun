package com.danggeun.market.interest;

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
public class InterestHistoryIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void 관심가지는_상품_조회1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/1/interests"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(3));
    }

    @Test
    void 관심가지는_상품_조회2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/2/interests"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[1].id").value(3));
    }
}
