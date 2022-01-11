package com.danggeun.market.product.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductQueryRepository {
}
