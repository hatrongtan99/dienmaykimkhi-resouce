package com.hatrongtan99.app.services;

import com.hatrongtan99.app.dto.productMetadataDto.ProductMetadataSaveOrUpdateDto;
import com.hatrongtan99.app.entity.*;
import com.hatrongtan99.app.repository.*;
import com.hatrongtan99.app.services.impl.MediaService;
import com.hatrongtan99.app.exception.DuplicatedException;
import com.hatrongtan99.app.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public abstract class ProductServiceHelper {

    private final ProductRepository productRepository;
    private final MediaService mediaService;
    private final BrandRepository brandRepository;
    private final ProductImageRepository productImageRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMetaDataRepository metaDataRepository;
    private boolean checkExistBySlug(String slug) {
        return this.productRepository.existsBySlug(slug);
    }

    private boolean checkExistBySku(String sku) {
        return this.productRepository.existsBySku(sku);
    }

    protected void validateNewProduct(String slug, String sku) {
        String message = null;
        if (checkExistBySlug(slug)) {
            message = "Slug already exist";
        }
        if (checkExistBySku(sku)) {
            if (message != null) {
                message += " and ";
            }
            message += "Sku already exist";
        }
        if (message != null) {
            throw new DuplicatedException(message);
        }
    }

    protected void setBrand(ProductEntity product, Long brandId) {
        BrandEntity brand = this.brandRepository.findById(brandId)
                .orElseThrow(() -> new NotFoundException("brand not found"));
        product.setBrandId(brand);
    }

    protected void setPrice(ProductEntity product, double price, String note) {
        PriceEntity priceEntity = PriceEntity.builder()
                .productId(product)
                .price(price)
                .note(note)
                .isActive(true)
                .build();
        if (product.getPrice() == null) {
            product.setPrice(List.of(priceEntity));
        } else {
            List<PriceEntity> listPrice = product.getPrice();
            listPrice.forEach(p -> p.setActive(false));
            product.getPrice().add(priceEntity);
        }
    }

    protected void setImages(ProductEntity product, List<Long> images) {
        if (images.isEmpty()) {
            return;
        }
        if (product.getImages() == null) {
            List<ProductImageEntity> listImages = images.stream()
                    .map(id -> ProductImageEntity.builder().imageId(id).productId(product).build()).toList();
            product.setImages(listImages);
            return;
        }
        // new product
        List<Long> existImagesId = product.getImages().stream().map(ProductImageEntity::getImageId).toList();
        List<Long> newImageId = images.stream().filter(id -> !existImagesId.contains(id)).toList();
        List<Long> deleteImageId = existImagesId.stream().filter(id -> !newImageId.contains(id)).toList();
        // delete image row
        this.productImageRepository.deleteByImageIdInAndProductId(deleteImageId, product);
        product.setImages(newImageId.stream().map(id -> ProductImageEntity.builder().productId(product).imageId(id).build()).toList());
    }

    protected void setCategories(ProductEntity product, List<Long> categoriesId) {
        if (categoriesId.isEmpty()) {
            return;
        }
        List<CategoryEntity> categories = this.categoryRepository.findAllById(categoriesId);
        if (categories.isEmpty()) {
            throw new NotFoundException("list category not found");
        }
        if (product.getCategories() == null) {
            product.setCategories(categories);
        } else {
            List<CategoryEntity> currentCategory = product.getCategories();
            List<CategoryEntity> listDelete = currentCategory.stream().filter(cate -> !categories.contains(cate)).toList();
            List<CategoryEntity> listSave = categories.stream().filter(cate -> !currentCategory.contains(cate)).toList();
            product.getCategories().removeAll(listDelete);
            product.getCategories().addAll(listSave);
        }
    }

    protected void setProductRelate(ProductEntity product, List<Long> productRelateId) {
        if (productRelateId.isEmpty()) {
            return;
        }

        List<ProductEntity> listProductRelate = this.productRepository.findAllById(productRelateId);
        if (listProductRelate.isEmpty()) {
            throw new NotFoundException("list product relate not found");
        }
        if (product.getProductRelate() == null) {
            product.setProductRelate(listProductRelate);
        } else {
            List<ProductEntity> currentProductRelate = product.getProductRelate();
            List<ProductEntity> listDelete = currentProductRelate.stream().filter(p -> !listProductRelate.contains(p)).toList();
            List<ProductEntity> listSave = listProductRelate.stream().filter(p -> !currentProductRelate.contains(p)).toList();
            product.getProductRelate().removeAll(listDelete);
            product.getProductRelate().addAll(listSave);
        }
    }

    protected void setMetadata(ProductEntity product, List<ProductMetadataSaveOrUpdateDto> listMetadata) {
        if (listMetadata.isEmpty()) {
            return;
        }
        List<ProductMetaDataEntity> metadataSave = new ArrayList<>();
        List<ProductMetaDataEntity> metadataUpdate = new ArrayList<>();
        for (ProductMetadataSaveOrUpdateDto metadata : listMetadata) {
            if (metadata.id() != null) {
                metadataUpdate.add(ProductMetaDataEntity.builder()
                        .productId(product)
                        .id(metadata.id())
                        .name(metadata.name())
                        .value(metadata.value())
                        .build());
            } else {
                metadataSave.add(ProductMetaDataEntity.builder()
                        .productId(product)
                        .name(metadata.name())
                        .value(metadata.value())
                        .build()
                );
            }
        }
        if (product.getMetadata() == null) {
            product.setMetadata(metadataSave);
        } else {
            List<ProductMetaDataEntity> currentMetadata = product.getMetadata();
            List<ProductMetaDataEntity> deleteMetadata = currentMetadata.stream().filter(m -> !metadataUpdate.contains(m)).toList();
            product.getMetadata().removeAll(deleteMetadata);
            product.getMetadata().addAll(metadataSave);
            this.metaDataRepository.saveAllAndFlush(metadataUpdate);
        }
    }
}
