package com.danggeun.market.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WithMockUser
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

    @Test
    void 제품_상세_조회1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.product_images[0]")
                        .value("https://s3.ap-northeast-2.amazonaws.com/danggeun/product/1/1"))
                .andExpect(jsonPath("$.data.product_images[1]")
                        .value("https://s3.ap-northeast-2.amazonaws.com/danggeun/product/1/2"))
                .andExpect(jsonPath("$.data.seller.id").value(1))
                .andExpect(jsonPath("$.data.seller.nickname").value("mynickname"))
                .andExpect(jsonPath("$.data.seller.profile").doesNotExist())
                .andExpect(jsonPath("$.data.name").value("nike sneakers"))
//                .andExpect(jsonPath("$.data.description").value("한번만 신은 나이키 신발입니다."))
//                .andExpect(jsonPath("$.data.category").value("디지털기기"))
                .andExpect(jsonPath("$.data.price").value("58000"))
                .andExpect(jsonPath("$.data.created_at").exists())
                .andExpect(jsonPath("$.data.another_products[0].id").value(2))
                .andExpect(jsonPath("$.data.another_products[0].thumbnail_image")
                        .value("https://s3.ap-northeast-2.amazonaws.com/danggeun/product/2/1"))
//                .andExpect(jsonPath("$.data.another_products[0].name").value("LG 냉장고"))
                .andExpect(jsonPath("$.data.another_products[0].money").value(1250000))
                .andExpect(jsonPath("$.data.another_products[1].id").value(3))
                .andExpect(jsonPath("$.data.another_products[1].thumbnail_image").doesNotExist())
//                .andExpect(jsonPath("$.data.another_products[1].name").value("조명등"))
                .andExpect(jsonPath("$.data.another_products[1].money").value(33000));
    }

    @Test
    void 판매자가_올린_상품조회1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/1/products?status=SELL"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(2))
                .andExpect(jsonPath("$.data[0].status").value("SELL"))
                .andExpect(jsonPath("$.data[1].id").value(1))
                .andExpect(jsonPath("$.data[1].status").value("SELL"));
    }

    @Test
    void 판매자가_올린_상품조회2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/1/products?status=SOLD"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(3))
                .andExpect(jsonPath("$.data[0].status").value("SOLD"));
    }

    @Test
    void 판매자가_올린_상품조회3() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/2/products?status=SOLD"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(7))
                .andExpect(jsonPath("$.data[0].status").value("SOLD"))
                .andExpect(jsonPath("$.data[1].id").value(5))
                .andExpect(jsonPath("$.data[1].status").value("SOLD"));
    }
}
