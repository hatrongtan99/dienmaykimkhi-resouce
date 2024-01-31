package com.hatrongtan99.app.services;

import com.hatrongtan99.app.dto.productDto.*;
import com.hatrongtan99.app.entity.ProductEntity;

public interface IProductCreateUpdateService {
    ProductEntity createProduct(ProductSaveDto product);
    ProductEntity updateProduct(Long id, ProductUpdateDto product);
    void deleteProduct(Long id);
}
