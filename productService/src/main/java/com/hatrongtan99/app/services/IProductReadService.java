package com.hatrongtan99.app.services;

import com.hatrongtan99.app.entity.ProductEntity;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IProductReadService {

    Page<ProductEntity> listProductWithPageBySlugCategory(int pageNumber, int pageLimit, String slugCate, Map<String, String> allStringQuery);
    Page<ProductEntity> listProductWithPageBySlugBrand(int pageNumber, int pageLimit, String slugBrand, Map<String, String> allStringQuery);
    ProductEntity getProductBySlug(String slug);
    ProductEntity getProductById(Long id);
}
