package com.hatrongtan99.app.services;

import com.hatrongtan99.app.dto.productDto.ProductGetListWithPageDto;
import com.hatrongtan99.app.dto.productDto.ProductLineCartResponseDto;
import com.hatrongtan99.app.entity.ProductEntity;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IProductReadService {

    ProductGetListWithPageDto listProductWithPageBySlugCategory(int pageNumber, int pageLimit, String slugCate, Map<String, String> allStringQuery);
    ProductGetListWithPageDto listProductWithPageBySlugBrand(int pageNumber, int pageLimit, String slugBrand, Map<String, String> allStringQuery);
    ProductEntity getProductBySlug(String slug);
    ProductEntity getProductById(Long id);
    ProductLineCartResponseDto getProductLineCart(Long id);
}
