package com.hatrongtan99.app.controller;

import com.hatrongtan99.app.dto.brandDto.BrandResponseDto;
import com.hatrongtan99.app.dto.brandDto.BrandSaveDto;
import com.hatrongtan99.app.dto.brandDto.BrandUpdateDto;
import com.hatrongtan99.app.entity.BrandEntity;
import com.hatrongtan99.app.services.IBrandService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/bff-admin/brands", "/bff-customer/brands"})
@AllArgsConstructor
public class BranchController {
    private IBrandService brandService;

    @GetMapping
    public ResponseEntity<List<BrandResponseDto>> getBrands() {
        List<BrandEntity> listBrand = this.brandService.getBrands();
        return ResponseEntity.ok(listBrand.stream().map(BrandResponseDto::mapToDto).toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BrandResponseDto> createBrand(
            @Valid @RequestBody BrandSaveDto brand
    ) {
        BrandEntity newBrand = this.brandService.create(brand);
        return ResponseEntity.status(HttpStatus.CREATED).body(BrandResponseDto.mapToDto(newBrand));
    }

    @PutMapping({"/{id}"})
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BrandResponseDto> updateBrand(
            @PathVariable("id") Long id,
            @Valid @RequestBody BrandUpdateDto brand
    ) {
        BrandEntity brandUpdate = this.brandService.update(id, brand);
        return ResponseEntity.ok(BrandResponseDto.mapToDto(brandUpdate));
    }
}
