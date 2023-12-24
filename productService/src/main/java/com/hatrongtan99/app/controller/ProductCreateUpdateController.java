package com.hatrongtan99.app.controller;

import com.hatrongtan99.app.dto.productDto.ProductResponseDto;
import com.hatrongtan99.app.dto.productDto.ProductSaveDto;
import com.hatrongtan99.app.entity.ProductEntity;
import com.hatrongtan99.app.services.IProductCreateUpdateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/bff-admin/products"})
@RequiredArgsConstructor
public class ProductCreateUpdateController {

    private final IProductCreateUpdateService productCreateUpdateService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(
            @Valid @RequestBody ProductSaveDto product
    ) {
        ProductEntity newProduct = this.productCreateUpdateService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductResponseDto.mapToDto(newProduct));
    }

    @PutMapping
    public ResponseEntity<ProductResponseDto> updateProduct(

    ) {
        return null;
    }

    @DeleteMapping("/{id}}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable("id") Long id
    ) {
        this.productCreateUpdateService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
