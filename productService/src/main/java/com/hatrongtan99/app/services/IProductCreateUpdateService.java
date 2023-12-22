package com.hatrongtan99.app.services;

import com.hatrongtan99.app.dto.productDto.*;

public interface IProductCreateUpdateService {
    ProductResponseDto createProduct(ProductSaveDto product);
    ProductResponseDto updateProduct(Long id, ProductUpdateDto product);
    void deleteProduct(Long id);

}
