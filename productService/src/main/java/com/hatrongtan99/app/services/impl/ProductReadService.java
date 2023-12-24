package com.hatrongtan99.app.services.impl;

import com.hatrongtan99.app.entity.CategoryEntity;
import com.hatrongtan99.app.entity.ProductEntity;
import com.hatrongtan99.app.repository.*;
import com.hatrongtan99.app.repository.filter.PriceRange;
import com.hatrongtan99.app.repository.spec.ProductSpec;
import com.hatrongtan99.app.services.IProductReadService;
import com.hatrongtan99.app.utils.CommonUtils;
import com.hatrongtan99.app.utils.Constant;
import com.hatrongtan99.app.exception.NotFoundException;
import jakarta.persistence.criteria.Join;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductReadService implements IProductReadService {

    private final ProductRepository productRepository;

    @Override
    public Page<ProductEntity> listProductWithPageBySlugCategory(int pageNumber, int pageLimit, String slugCate, Map<String, String> allStringQuery) {
        Specification<ProductEntity> spec = Specification.where((root, query, criteriaBuilder) -> {
            Join<ProductEntity, CategoryEntity> joinCate = root.join("categories");
            return criteriaBuilder.equal(joinCate.get("slug"), slugCate);
        });
        spec = this.getSpecificationFilter(spec, allStringQuery);
        List<ProductEntity> productFiltered = this.productRepository.findAll(spec);
        List<Long> listId = productFiltered.stream().map(ProductEntity::getId).toList();

        Sort priceSort = CommonUtils.getSort(allStringQuery.get(Constant.FILTER_QUERY_SORT));

        Pageable pageable = PageRequest.of(pageNumber, pageLimit, priceSort);
        return this.productRepository.findByIdIn(listId, pageable);
    }

    @Override
    public Page<ProductEntity> listProductWithPageBySlugBrand(int pageNumber, int pageLimit, String slugBrand, Map<String, String> stringQuery) {
        Sort sortPrice = CommonUtils.getSort(stringQuery.get(Constant.FILTER_QUERY_SORT));
        Pageable pageable = PageRequest.of(pageNumber, pageLimit, sortPrice);
        return this.productRepository.findByBrandSlugWithPriceActive(slugBrand, pageable);
    }

    @Override
    public ProductEntity getProductBySlug(String slug) {
        return this.productRepository.findBySlugAndIsActiveIsTrue(slug)
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @Override
    public ProductEntity getProductById(Long id) {
        return this.productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
    }

    private List<PriceRange> getPriceRanges(String queryString) {
        if (queryString == null || queryString.isEmpty()) {
            return Collections.emptyList();
        }
        List<PriceRange> result = new ArrayList<>();
        String[] listRange = queryString.trim().split(",");
        for (String item : listRange) {
            if (item.contains("-")) {
                String[] rangeValue = item.trim().split("-");
                double startRange = Double.parseDouble(rangeValue[0]);
                double endRange = Double.parseDouble(rangeValue[1]);
                result.add(new PriceRange(startRange, endRange));
            }
        }
        return result;
    }

    private List<Long> getListIdsFromString(String queryString) {
        if (queryString == null || queryString.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.stream(queryString.trim().split(",")).map(Long::parseLong).toList();
    }

    private Specification<ProductEntity> getSpecificationFilter(Specification<ProductEntity> spec, Map<String, String> allStringQuery) {
        if (allStringQuery.get(Constant.FILTER_QUERY_PRICE) != null) {
            String queryString = allStringQuery.get(Constant.FILTER_QUERY_PRICE);
            spec = spec.and(ProductSpec.filterByPrice(this.getPriceRanges(queryString)));
        }

        if (allStringQuery.get(Constant.FILTER_QUERY_BRAND) != null) {
            String queryString = allStringQuery.get(Constant.FILTER_QUERY_BRAND);
            spec = spec.and(ProductSpec.filterBrandId(this.getListIdsFromString(queryString)));
        }

        if (allStringQuery.get(Constant.FILTER_QUERY_FILTER) != null) {
            String queryString = allStringQuery.get(Constant.FILTER_QUERY_FILTER);
            spec = spec.and(ProductSpec.filterByFilterId(this.getListIdsFromString(queryString)));
        }
        return spec;
    }


}
