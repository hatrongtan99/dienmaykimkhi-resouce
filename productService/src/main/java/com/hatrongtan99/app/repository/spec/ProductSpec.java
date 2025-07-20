package com.hatrongtan99.app.repository.spec;

import com.hatrongtan99.app.entity.BrandEntity;
import com.hatrongtan99.app.entity.PriceEntity;
import com.hatrongtan99.app.entity.ProductEntity;
import com.hatrongtan99.app.entity.filter.ProductFilter;
import com.hatrongtan99.app.repository.filter.PriceRange;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ProductSpec {

    public static Specification<ProductEntity> filterBrandId(List<Long> ids) {
        return (root, query, criteriaBuilder) -> {
            if (ids.isEmpty()) {
                return query.getRestriction();
            }
            Join<ProductEntity, BrandEntity> brandJoin = root.join("brandId");
            return brandJoin.get("id").in(ids);
        };
    }

    public static Specification<ProductEntity> filterByFilterId(List<Long> ids) {
        return (root, query, criteriaBuilder) -> {
            if (ids.isEmpty()) {
                return query.getRestriction();
            }
            Join<ProductEntity, ProductFilter> filterJoin = root.join("filters");
            return filterJoin.get("id").in(ids);
        };
    }

    public static Specification<ProductEntity> filterByPrice(List<PriceRange> priceRangeList) {
        return (root, query, criteriaBuilder) -> {
            Join<ProductEntity, PriceEntity> pricejoin = root.join("price");
            List<Predicate> predicates = priceRangeList.stream().map(range -> criteriaBuilder
                    .between(pricejoin.get("price"), range.getStartRange(), range.getEndRange())).toList();
            return criteriaBuilder.and(
                    criteriaBuilder.equal(pricejoin.get("isActive"), true),
                    criteriaBuilder.or(predicates.toArray(new Predicate[0])));
        };
    }

}
