package com.hatrongtan99.app.repository.filter;

import com.hatrongtan99.app.entity.filter.ProductFilter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductFilterRepository extends JpaRepository<ProductFilter, Long> {
}
