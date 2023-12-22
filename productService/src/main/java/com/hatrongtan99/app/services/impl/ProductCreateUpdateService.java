package com.hatrongtan99.app.services.impl;

import com.hatrongtan99.app.dto.productDto.*;
import com.hatrongtan99.app.entity.*;
import com.hatrongtan99.app.repository.*;
import com.hatrongtan99.app.services.IProductCreateUpdateService;
import com.hatrongtan99.app.services.ProductServiceHelper;
import com.hatrongtan99.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductCreateUpdateService extends ProductServiceHelper implements IProductCreateUpdateService {

    private final ProductRepository productRepository;
    private final MediaService mediaService;

    public ProductCreateUpdateService(
            ProductRepository productRepository,
            MediaService mediaService,
            BrandRepository brandRepository,
            ProductImageRepository productImageRepository,
            CategoryRepository categoryRepository,
            ProductMetaDataRepository metaDataRepository) {
        super(productRepository, mediaService, brandRepository, productImageRepository, categoryRepository, metaDataRepository);
        this.productRepository = productRepository;
        this.mediaService = mediaService;
    }

    @Override
    @Transactional
    public ProductResponseDto createProduct(ProductSaveDto product) {
        this.validateNewProduct(product.slug(), product.sku());
        //create description row
        DescriptionEntity description = DescriptionEntity.builder().content(product.description()).build();
        // save thumbnail image
        Long thumbnailId = this.mediaService.saveFile(product.thumbnail());

        ProductEntity newProduct = ProductEntity.builder()
                .name(product.name())
                .shortDescription(product.shortDescription())
                .descriptionId(description)
                .sku(product.sku())
                .slug(product.slug())
                .thumbnailId(thumbnailId)
                .isAvailInStock(product.isAvailInStock())
                .build();
        // create first price row
        setPrice(newProduct, product.price(), "create product");
        // save brand
        setBrand(newProduct, product.brandId());
        // save images
        setImages(newProduct, product.images());
        // save categories
        setCategories(newProduct, product.categoryId());

        if (product.productRelate() != null) {
            setProductRelate(newProduct, product.productRelate());
        }

        if (product.metadata() != null) {
            setMetadata(newProduct, product.metadata());
        }



        this.productRepository.saveAndFlush(newProduct);

        return ProductResponseDto.mapToDto(newProduct);
    }


    @Override
    public ProductResponseDto updateProduct(Long id, ProductUpdateDto product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        ProductEntity product = this.productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("product not found"));

        product.setActive(false);
    }


}
