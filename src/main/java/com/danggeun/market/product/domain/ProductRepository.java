package com.danggeun.market.product.domain;

import com.danggeun.market.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductQueryRepository {
    List<Product> findProductBySeller(User seller);

    @Query("SELECT P FROM Product P " +
            "LEFT JOIN FETCH P.productImages " +
            "JOIN FETCH P.seller " +
            "JOIN FETCH P.category " +
            "WHERE P.id = :productId")
    Optional<Product> findProductByIdWithProductImagesAndSellerAndCategory(@Param("productId") Long productId);
}
