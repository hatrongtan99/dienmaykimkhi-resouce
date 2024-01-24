package com.hatrongtan99.app.controller;

import com.hatrongtan99.app.dto.productDto.ProductDetailPageResponseDto;
import com.hatrongtan99.app.dto.productDto.ProductGetListWithPageDto;
import com.hatrongtan99.app.dto.productDto.ProductLineCartResponseDto;
import com.hatrongtan99.app.entity.ProductEntity;
import com.hatrongtan99.app.services.IProductReadService;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping({"/bff-admin/products", "/bff-customer/products"})
@RequiredArgsConstructor
public class ProductReadController {

    private final IProductReadService productReadService;

    @GetMapping({"/category-slug/{categorySlug}"})
    public ResponseEntity<ProductGetListWithPageDto> getProductWithCategorySlug(
            @PathVariable("categorySlug") String categorySlug,
            @RequestParam(name = "page", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "limit", required = false, defaultValue = "5") int pageLimit,
            @RequestParam Map<String, String> allRequestParams
    ) {
        return ResponseEntity.ok(this.productReadService.listProductWithPageBySlugCategory(pageNumber, pageLimit, categorySlug, allRequestParams));
    }

    @GetMapping({"/brand-slug/{brand}"})
    public ResponseEntity<ProductGetListWithPageDto> getProductWithBrandSlug(
            @PathVariable("brand") String brandSlug,
            @RequestParam(name = "page", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "limit", required = false, defaultValue = "5") int pageLimit,
            @RequestParam Map<String, String> allRequestParams
    ) {
        return ResponseEntity.ok(this.productReadService.listProductWithPageBySlugBrand(pageNumber, pageLimit, brandSlug, allRequestParams));
    }

    @GetMapping("/{slug}")
    public ResponseEntity<ProductDetailPageResponseDto> getDetailProductBySlug(
            @PathVariable("slug") String slug
    ) {
        ProductEntity product = this.productReadService.getProductBySlug(slug);
        return ResponseEntity.ok(ProductDetailPageResponseDto.mapToDto(product));
    }

    @GetMapping("/cart-item/{productId}")
    public ResponseEntity<ProductLineCartResponseDto> detailCartItem(
            @PathVariable("productId") Long productId
    ) {
        ProductLineCartResponseDto responseDto = this.productReadService.getProductLineCart(productId);
        return ResponseEntity.ok(responseDto);
    }

}

