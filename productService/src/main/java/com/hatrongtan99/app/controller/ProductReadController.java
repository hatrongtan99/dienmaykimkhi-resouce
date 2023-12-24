package com.hatrongtan99.app.controller;

import com.hatrongtan99.app.dto.paginationDto.MetadataDto;
import com.hatrongtan99.app.dto.productDto.ProductCardDto;
import com.hatrongtan99.app.dto.productDto.ProductDetailPageResponseDto;
import com.hatrongtan99.app.dto.productDto.ProductGetListWithPageDto;
import com.hatrongtan99.app.entity.ProductEntity;
import com.hatrongtan99.app.services.IProductReadService;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping({"/bff-admin/products", "/bff-customer/products"})
@RequiredArgsConstructor
public class ProductReadController {

    private final IProductReadService productReadService;

    @GetMapping({"/category-slug/{categorySlug}"})
    public ResponseEntity<ProductGetListWithPageDto> getProductWithCategorySlug(
            @PathVariable("categorySlug") String categorySlug,
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageLimit", required = false, defaultValue = "5") int pageLimit,
            @RequestParam Map<String, String> allRequestParams
    ) {
        Page<ProductEntity> page = this.productReadService.listProductWithPageBySlugCategory(pageNumber, pageLimit, categorySlug, allRequestParams);
        MetadataDto metadataDto = new MetadataDto(
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                (int) page.getTotalElements()
        );
        List<ProductCardDto> records = page.getContent().stream().map(p -> ProductCardDto.mapToDto(p, p.getPrice().get(0).getPrice())).toList();
        return ResponseEntity.ok(new ProductGetListWithPageDto(records, metadataDto));
    }

    @GetMapping({"/brand-slug/{brand}"})
    public ResponseEntity<ProductGetListWithPageDto> getProductWithBrandSlug(
            @PathVariable("brand") String brandSlug,
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageLimit", required = false, defaultValue = "5") int pageLimit,
            @RequestParam Map<String, String> allRequestParams
    ) {
        Page<ProductEntity> page = this.productReadService.listProductWithPageBySlugBrand(pageNumber, pageLimit, brandSlug, allRequestParams);
        MetadataDto metadataDto = new MetadataDto(
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                (int) page.getTotalElements()
        );
        List<ProductCardDto> records = page.getContent().stream().map(p -> ProductCardDto.mapToDto(p, p.getPrice().get(0).getPrice())).toList();

        return ResponseEntity.ok(new ProductGetListWithPageDto(records, metadataDto));
    }

    @GetMapping("/{slug}")
    public ResponseEntity<ProductDetailPageResponseDto> getDetailProductBySlug(
            @PathVariable("slug") String slug
    ) {
        ProductEntity product = this.productReadService.getProductBySlug(slug);
        return ResponseEntity.ok(ProductDetailPageResponseDto.mapToDto(product));
    }

}

