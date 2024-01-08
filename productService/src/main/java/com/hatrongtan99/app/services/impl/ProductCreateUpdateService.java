package com.hatrongtan99.app.services.impl;

import com.hatrongtan99.app.dto.productAttributeDto.ProductAttributeListItemSaveDto;
import com.hatrongtan99.app.dto.productDto.*;
import com.hatrongtan99.app.entity.*;
import com.hatrongtan99.app.repository.*;
import com.hatrongtan99.app.services.IProductAttributeService;
import com.hatrongtan99.app.services.IProductCreateUpdateService;
import com.hatrongtan99.app.services.ProductServiceHelper;
import com.hatrongtan99.app.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductCreateUpdateService extends ProductServiceHelper implements IProductCreateUpdateService {

    private final ProductRepository productRepository;

    public ProductCreateUpdateService(
            ProductRepository productRepository,
            MediaService mediaService,
            BrandRepository brandRepository,
            ProductImageRepository productImageRepository,
            CategoryRepository categoryRepository,
            ProductMetaDataRepository metaDataRepository,
            IProductAttributeService productAttributeService) {
        super(productRepository, mediaService, brandRepository, productImageRepository, categoryRepository, metaDataRepository, productAttributeService);
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ProductEntity createProduct(ProductSaveDto product) {
        this.validateNewProduct(product.slug(), product.sku());
        //create description row
        DescriptionEntity description = DescriptionEntity.builder().content(product.description()).build();
        // save thumbnail image
        ProductEntity newProduct = ProductEntity.builder()
                .name(product.name())
                .shortDescription(product.shortDescription())
                .descriptionId(description)
                .sku(product.sku())
                .slug(product.slug())
                .guarantee(product.guarantee())
                .thumbnailId(product.thumbnailId())
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

        ProductEntity mainSaveProduct = this.productRepository.save(newProduct);

        if (product.productRelate() != null) {
            setProductRelate(mainSaveProduct, product.productRelate());
        }

        if (product.metadata() != null) {
            setMetadata(mainSaveProduct, product.metadata());
        }

        if (product.attributes() != null) {
            setProductAttributes(mainSaveProduct, product.attributes());
        }

        return mainSaveProduct;
    }

    @Override
    public ProductEntity updateProduct(Long id, ProductUpdateDto product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        ProductEntity product = this.productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("product not found"));

        product.setActive(false);
    }


}
