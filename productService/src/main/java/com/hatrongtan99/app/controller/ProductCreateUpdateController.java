package com.hatrongtan99.app.controller;

import com.hatrongtan99.app.dto.productDto.ProductSaveDto;
import com.hatrongtan99.app.services.IProductCreateUpdateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/bff-admin/products"})
@RequiredArgsConstructor
public class ProductCreateUpdateController {

    private final IProductCreateUpdateService productCreateUpdateService;

    @PostMapping
    public ResponseEntity<?> createProduct(
            @Valid @RequestBody ProductSaveDto product
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productCreateUpdateService.createProduct(product));
    }
}
