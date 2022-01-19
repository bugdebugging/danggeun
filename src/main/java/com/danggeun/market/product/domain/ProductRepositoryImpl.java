package com.danggeun.market.product.domain;


import com.danggeun.market.category.domain.Category;
import com.danggeun.market.product.dto.ProductSummaryResponse;
import com.danggeun.market.product.service.dto.ProductSearchCommand;
import com.danggeun.market.user.domain.User;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
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
    public List<ProductSummaryResponse> findProductSummaries(ProductSearchCommand command, int size) {
        return queryFactory.select(Projections.constructor(ProductSummaryResponse.class,
                product, reply.countDistinct(), interestHistory.countDistinct()))
                .from(product)
                .where(productIdLt(command.getProductId()),
                        productStatusEq(command.getProductStatus()),
                        productCategoryEq(command.getCategory()),
                        productSellerEq(command.getSeller()))
                .leftJoin(reply).on(product.id.eq(reply.productId))
                .leftJoin(interestHistory).on(product.id.eq(interestHistory.product.id))
                .groupBy(product.id)
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

    private BooleanExpression productCategoryEq(Category category) {
        if (category == null) {
            return null;
        }
        return product.category.eq(category);
    }

    private BooleanExpression productSellerEq(User seller) {
        if (seller == null) {
            return null;
        }
        return product.seller.eq(seller);
    }
}
