package com.hatrongtan99.app.services;

import com.hatrongtan99.app.dto.productDto.ProductDetailResponseDto;
import com.hatrongtan99.app.dto.productDto.ProductGetListWithPageDto;
import com.hatrongtan99.app.dto.productDto.ProductResponseDto;

import java.util.Map;

public interface IProductReadService {

    ProductGetListWithPageDto listProductWithPageBySlugCategory(int pageNumber, int pageLimit, String slugCate, Map<String, String> allStringQuery);
    ProductGetListWithPageDto listProductWithPageBySlugBrand(int pageNumber, int pageLimit, String slugBrand, Map<String, String> allStringQuery);
    ProductDetailResponseDto getProductBySlug(String slug);
    ProductResponseDto getProductById(Long id);
}
