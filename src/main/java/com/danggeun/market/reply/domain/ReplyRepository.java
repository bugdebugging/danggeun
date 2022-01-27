package com.danggeun.market.reply.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    @Query("SELECT R,U " +
            "FROM Reply R INNER JOIN User U ON R.author = U.id " +
            "WHERE R.product.id= :productId " +
            "ORDER BY R.id DESC")
    List<Object[]> findRepliesWithWriterTest(@Param("productId") Long productId,
                                             Pageable pageable);

}
