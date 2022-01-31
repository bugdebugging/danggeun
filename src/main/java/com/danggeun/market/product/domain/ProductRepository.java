package com.danggeun.market.product.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductQueryRepository {
    List<Product> findProductBySellerId(Long sellerId);

    @Query("SELECT P FROM Product P " +
            "LEFT JOIN FETCH P.productImages " +
            "JOIN FETCH P.category " +
            "WHERE P.id = :productId")
    Optional<Product> findProductByIdWithProductImagesAndCategory(@Param("productId") Long productId);

    @Query("SELECT distinct P FROM Product P " +
            "LEFT JOIN FETCH P.interestHistories " +
            "WHERE P.id = :productId")
    Optional<Product> findProductByIdWithInterestHistories(@Param("productId") Long productId);
}
