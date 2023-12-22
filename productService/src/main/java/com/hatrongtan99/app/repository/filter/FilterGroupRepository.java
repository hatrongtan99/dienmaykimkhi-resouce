package com.hatrongtan99.app.repository.filter;

import com.hatrongtan99.app.entity.filter.FilterGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FilterGroupRepository extends JpaRepository<FilterGroup, Long> {
    boolean existsByName(String name);
}
