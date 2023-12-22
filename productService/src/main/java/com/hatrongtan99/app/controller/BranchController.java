package com.hatrongtan99.app.controller;

import com.hatrongtan99.app.dto.brandDto.BrandResponseDto;
import com.hatrongtan99.app.dto.brandDto.BrandSaveDto;
import com.hatrongtan99.app.dto.brandDto.BrandUpdateDto;
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
        return ResponseEntity.ok(this.brandService.getBrands());
    }
    @PostMapping
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BrandResponseDto> createBrand(
            @Valid @RequestBody BrandSaveDto brand
            ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.brandService.create(brand));
    }

    @PutMapping({"/{id}"})
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BrandResponseDto> updateBrand(
            @PathVariable("id") Long id,
            @Valid @RequestBody BrandUpdateDto brand
            ) {
        return ResponseEntity.ok(this.brandService.update(id, brand));
    }
}
