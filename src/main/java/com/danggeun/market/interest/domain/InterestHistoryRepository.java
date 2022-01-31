package com.danggeun.market.interest.domain;

import com.danggeun.market.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InterestHistoryRepository extends JpaRepository<InterestHistory, Long> {
    @Query("SELECT IH,(SELECT COUNT(R) FROM Reply R WHERE R.product=IH.product)," +
            "(SELECT COUNT(IH2) FROM InterestHistory IH2 WHERE IH2.product=IH.product)" +
            " FROM InterestHistory IH JOIN FETCH IH.product WHERE IH.userId = :userId")
    List<Object[]> searchInterestProduct(@Param("userId") Long userId);

    Optional<InterestHistory> findByUserIdAndProduct(Long userId, Product product);
}
