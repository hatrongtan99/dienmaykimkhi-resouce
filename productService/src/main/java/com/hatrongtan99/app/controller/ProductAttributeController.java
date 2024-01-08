package com.hatrongtan99.app.controller;

import com.hatrongtan99.app.dto.ProductAttributeItemDto.ProductAttributeItemResponseDto;
import com.hatrongtan99.app.dto.productAttributeDto.AttributeSaveDto;
import com.hatrongtan99.app.dto.productAttributeDto.AttributeUpdateDto;
import com.hatrongtan99.app.dto.productAttributeDto.ProductAttributeListItemResponseDto;
import com.hatrongtan99.app.entity.ProductAttributeEntity;
import com.hatrongtan99.app.services.IProductAttributeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"/bff-admin/attribute", "/bff-customer/attribute"})
@RequiredArgsConstructor
public class ProductAttributeController {

    private final IProductAttributeService productAttributeService;

    @PostMapping
    public ResponseEntity<Long> createNewProductAttribute(
            @Valid AttributeSaveDto attribute
    ) {
        Long id = this.productAttributeService.createAttribute(attribute);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<List<ProductAttributeListItemResponseDto>> getListAttribute(
            @PathVariable("id") Long productId
    ) {
        return ResponseEntity.ok(this.productAttributeService.getAttributeByProductId(productId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateAttribute(
            @PathVariable("id") Long id,
            @Valid AttributeUpdateDto attribute
    ) {
        Long idUpdate = this.productAttributeService.updateAttribute(id, attribute);
        return ResponseEntity.ok(idUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttribute(
            @PathVariable("id") Long id
    ) {
        this.productAttributeService.removeAttribute(id);
        return ResponseEntity.noContent().build();
    }
}
