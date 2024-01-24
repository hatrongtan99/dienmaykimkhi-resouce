package com.hatrongtan99.app.services.impl;

import com.hatrongtan99.app.dto.brandDto.BrandCardDto;
import com.hatrongtan99.app.dto.mediaDto.ThumbnailResponseDto;
import com.hatrongtan99.app.dto.paginationDto.MetadataDto;
import com.hatrongtan99.app.dto.productDto.ProductCardDto;
import com.hatrongtan99.app.dto.productDto.ProductGetListWithPageDto;
import com.hatrongtan99.app.dto.productDto.ProductLineCartResponseDto;
import com.hatrongtan99.app.entity.BrandEntity;
import com.hatrongtan99.app.entity.CategoryEntity;
import com.hatrongtan99.app.entity.PriceEntity;
import com.hatrongtan99.app.entity.ProductEntity;
import com.hatrongtan99.app.repository.*;
import com.hatrongtan99.app.repository.filter.PriceRange;
import com.hatrongtan99.app.repository.spec.ProductSpec;
import com.hatrongtan99.app.services.IInventoryService;
import com.hatrongtan99.app.services.IMediaService;
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
    private final IInventoryService inventoryService;
    private final IMediaService mediaService;
    private final PriceRepository priceRepository;
    @Override
    public ProductGetListWithPageDto listProductWithPageBySlugCategory(int pageNumber, int pageLimit, String slugCate, Map<String, String> allStringQuery) {
//        SELECT * FROM product AS p LEFT JOIN product_category AS pc on p.id = pc.product_id left join category as c0 on pc.category_id = c0.id where c0.slug in (
//                with RECURSIVE cte as (
//                select c1.slug from category as c1 where c1.slug = "may-khoan"
//                UNION
//                SELECT c2.slug from category as c2 INNER JOIN category as c3 on c2.parent_id = c3.id where c3.slug = "may-khoan"
//        ) select * from cte
//);

        Specification<ProductEntity> spec = Specification.where((root, query, criteriaBuilder) -> {

            // join if match child slug category
            Join<ProductEntity, CategoryEntity> joinCate = root.join("categories");
            // join if match parent slug category
            Join<CategoryEntity, CategoryEntity> joinParenCate = joinCate.join("parentId");
            return criteriaBuilder.or(
                    criteriaBuilder.equal(joinCate.get("slug"), slugCate),
                    criteriaBuilder.equal(joinParenCate.get("slug"), slugCate)
            );
        });
        spec = this.getSpecificationFilter(spec, allStringQuery);
        List<ProductEntity> productFiltered = this.productRepository.findAll(spec);
        List<Long> listId = productFiltered.stream().map(ProductEntity::getId).toList();

        Sort priceSort = CommonUtils.getSort(allStringQuery.get(Constant.FILTER_QUERY_SORT));

        Pageable pageable = PageRequest.of(pageNumber, pageLimit, priceSort);
        Page<ProductEntity> page =  this.productRepository.findByIdIn(listId, pageable);
        return this.getProductResponseWithPage(page);
    }

    @Override
    public ProductGetListWithPageDto listProductWithPageBySlugBrand(int pageNumber, int pageLimit, String slugBrand, Map<String, String> stringQuery) {
        Sort sortPrice = CommonUtils.getSort(stringQuery.get(Constant.FILTER_QUERY_SORT));
        Pageable pageable = PageRequest.of(pageNumber, pageLimit, sortPrice);
        Page<ProductEntity> page = this.productRepository.findByBrandSlugWithPriceActive(slugBrand, pageable);

        return this.getProductResponseWithPage(page);
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

    @Override
    public ProductLineCartResponseDto getProductLineCart(Long id) {
        ProductEntity product = this.productRepository.findByIdAndIsActiveIsTrue(id)
                .orElseThrow(() -> new NotFoundException("not found"));
        PriceEntity price = this.priceRepository.findByProductIdAndIsActiveIsTrue(product);
        String urlThumbnail = this.mediaService.getFile(product.getThumbnailId()).url();
        return new ProductLineCartResponseDto(
                product.getId(),
                product.getName(),
                product.getSlug(),
                price.getPrice(),
                urlThumbnail
        );
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

    private ProductGetListWithPageDto getProductResponseWithPage(Page<ProductEntity> page) {
        MetadataDto metadataDto = MetadataDto.mapToDto(page);
        List<ProductEntity> productEntities = page.getContent();
        List<ProductCardDto> records = new ArrayList<>();
        for (ProductEntity product : productEntities) {
            BrandEntity brand = product.getBrandId();
            String urlThumbnail = this.mediaService.getFile(product.getThumbnailId()).url();
            String urlBrandThumbnail = this.mediaService.getFile(brand.getThumbnailId()).url();

            records.add(new ProductCardDto(
                    product.getId(),
                    product.getName(),
                    product.getSlug(),
                    urlThumbnail,
                    product.getPrice().get(0).getPrice(),
                    new BrandCardDto(brand.getId(), brand.getName(), brand.getSlug(), urlBrandThumbnail)
            ));
        }
        return new ProductGetListWithPageDto(records, metadataDto);
    }


}
