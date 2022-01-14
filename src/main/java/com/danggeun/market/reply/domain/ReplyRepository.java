package com.danggeun.market.reply.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    @Query("SELECT R FROM Reply R " +
            "JOIN FETCH R.writer " +
            "WHERE R.productId= :productId " +
            "ORDER BY R.id DESC")
    List<Reply> findRepliesWithWriter(@Param("productId") Long productId,
                                      Pageable pageable);
}
