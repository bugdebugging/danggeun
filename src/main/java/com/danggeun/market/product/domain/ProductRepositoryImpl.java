package com.danggeun.market.product.domain;


import com.danggeun.market.product.dto.ProductSummaryResponse;
import com.danggeun.market.product.service.dto.ProductSearchCommand;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.danggeun.market.product.domain.QProduct.product;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ProductSummaryResponse> findProductSummaries(ProductSearchCommand command, int size) {
        return queryFactory.select(Projections.constructor(ProductSummaryResponse.class
                , product, product.replies.size().castToNum(Long.class), product.interestHistories.size().castToNum(Long.class)))
                .from(product)
                .where(productIdLt(command.getProductId()),
                        productStatusEq(command.getProductStatus()),
                        productCategoryEq(command.getCategoryId()),
                        productSellerEq(command.getSellerId()))
                .orderBy(product.id.desc())
                .limit(size)
                .fetch();
    }

    private BooleanExpression productIdLt(Long lastProductId) {
        if (lastProductId == null) {
            return null;
        }
        return product.id.lt(lastProductId);
    }

    private BooleanExpression productStatusEq(ProductStatus status) {
        if (status == null) {
            return null;
        }
        return product.status.eq(status);
    }

    private BooleanExpression productCategoryEq(Long categoryId) {
        if (categoryId == null) {
            return null;
        }
        return product.category.id.eq(categoryId);
    }

    private BooleanExpression productSellerEq(Long sellerId) {
        if (sellerId == null) {
            return null;
        }
        return product.sellerId.eq(sellerId);
    }
}
