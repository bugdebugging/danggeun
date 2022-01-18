package com.danggeun.market.interest.domain;

import com.danggeun.market.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InterestHistoryRepository extends JpaRepository<InterestHistory, Long> {
    @Query("SELECT IH,(SELECT COUNT(R) FROM Reply R WHERE R.productId=IH.product.id)," +
            "(SELECT COUNT(IH2) FROM InterestHistory IH2 WHERE IH2.product.id=IH.product.id)" +
            " FROM InterestHistory IH JOIN FETCH IH.product WHERE IH.user = :user")
    List<Object[]> searchInterestProduct(@Param("user") User user);

}
