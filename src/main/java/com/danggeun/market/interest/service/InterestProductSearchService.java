package com.danggeun.market.interest.service;

import com.danggeun.market.interest.domain.InterestHistory;
import com.danggeun.market.interest.domain.InterestHistoryRepository;
import com.danggeun.market.product.dto.ProductSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InterestProductSearchService {
    private final InterestHistoryRepository interestHistoryRepository;

    public List<ProductSummaryResponse> searchInterestProduct(Long userId) {
        checkArgument(userId != null, "사용자의 id는 필수입니다.");
        List<Object[]> result = interestHistoryRepository.searchInterestProduct(userId);
        return result.stream()
                .map(objects -> new ProductSummaryResponse(((InterestHistory) objects[0]).getProduct(),
                        (Long) objects[1],
                        (Long) objects[2]))
                .collect(Collectors.toList());
    }
}
