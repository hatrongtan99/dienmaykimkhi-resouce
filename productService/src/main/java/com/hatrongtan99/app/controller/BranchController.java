package com.hatrongtan99.app.controller;

import com.hatrongtan99.app.dto.brandDto.BrandResponseDto;
import com.hatrongtan99.app.dto.brandDto.BrandSaveDto;
import com.hatrongtan99.app.dto.brandDto.BrandUpdateDto;
import com.hatrongtan99.app.dto.mediaDto.MediaResponseDto;
import com.hatrongtan99.app.entity.BrandEntity;
import com.hatrongtan99.app.services.IBrandService;
import com.hatrongtan99.app.services.IMediaService;
import com.hatrongtan99.app.services.impl.MediaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"/bff-admin/brands", "/bff-customer/brands"})
@AllArgsConstructor
public class BranchController {
    private IBrandService brandService;
    private IMediaService mediaService;

    @GetMapping
    public ResponseEntity<List<BrandResponseDto>> getBrands() {
        List<BrandEntity> listBrand = this.brandService.getBrands();
        List<BrandResponseDto> res = new ArrayList<>();
        for (BrandEntity brand : listBrand) {
            MediaResponseDto mediaResponse = this.mediaService.getFile(brand.getThumbnailId());
            res.add(BrandResponseDto.mapToDto(brand, mediaResponse));
        }
        return ResponseEntity.ok(res);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BrandResponseDto> createBrand(
            @Valid @RequestBody BrandSaveDto brand
    ) {
        BrandEntity newBrand = this.brandService.create(brand);
        return ResponseEntity.status(HttpStatus.CREATED).body(BrandResponseDto.mapToDto(newBrand));
    }

    @PutMapping({"/{id}"})
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BrandResponseDto> updateBrand(
            @PathVariable("id") Long id,
            @Valid @RequestBody BrandUpdateDto brand
    ) {
        BrandEntity brandUpdate = this.brandService.update(id, brand);
        return ResponseEntity.ok(BrandResponseDto.mapToDto(brandUpdate));
    }

    @GetMapping("/{slug}")
    public ResponseEntity<BrandResponseDto> getBrandBySlug(
            @PathVariable("slug") String slug
    ) {
        BrandEntity brandUpdate = this.brandService.findBySlug(slug);
        return ResponseEntity.ok(BrandResponseDto.mapToDto(brandUpdate));
    }

    @PostMapping("/test")
    public String test(
            @RequestBody Object body
    ) {
        return "success";
    }
}
