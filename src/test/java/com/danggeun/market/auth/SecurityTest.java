package com.danggeun.market.auth;

import com.danggeun.market.user.dto.AuthLoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTest {
    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void 인증없이_product_summaries_요청_Forbidden() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/products"))
                .andExpect(status().isForbidden());
    }

    @Test
    void 로그인후_토큰으로_product_summaries_요청_성공() throws Exception {
        final String username = "youremail@kw.ac.kr";
        final String password = "sample_pw2";
        AuthLoginRequest authLoginRequest = new AuthLoginRequest(username, password);

        final String jsonRequest = objectMapper.writeValueAsString(authLoginRequest);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().exists("Authorization"))
                .andReturn();

        String token = result.getResponse().getHeader("Authorization");
        mvc.perform(MockMvcRequestBuilders.get("/api/products")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }
}
