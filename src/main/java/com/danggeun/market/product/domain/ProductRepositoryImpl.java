package com.danggeun.market.product.domain;


import com.danggeun.market.product.dto.ProductSummaryResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.danggeun.market.interest.domain.QInterestHistory.interestHistory;
import static com.danggeun.market.product.domain.QProduct.product;
import static com.danggeun.market.reply.domain.QReply.reply;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ProductSummaryResponse> findProductSummaries(Long productId, int size) {
        BooleanBuilder dynamicLtId = new BooleanBuilder();
        if (productId != null) {
            dynamicLtId.and(product.id.lt(productId));
        }
        return queryFactory.select(Projections.constructor(ProductSummaryResponse.class,
                product, reply.countDistinct(), interestHistory.countDistinct()))
                .from(product)
                .where(dynamicLtId)
                .leftJoin(reply).on(product.id.eq(reply.productId))
                .leftJoin(interestHistory).on(product.id.eq(interestHistory.product.id))
                .groupBy(product.id)
                .orderBy(product.id.desc())
                .limit(size)
                .fetch();
    }
}
