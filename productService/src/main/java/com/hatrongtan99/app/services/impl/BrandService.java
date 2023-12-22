package com.hatrongtan99.app.services.impl;

import com.hatrongtan99.app.dto.brandDto.BrandResponseDto;
import com.hatrongtan99.app.dto.brandDto.BrandSaveDto;
import com.hatrongtan99.app.dto.brandDto.BrandUpdateDto;
import com.hatrongtan99.app.entity.BrandEntity;
import com.hatrongtan99.app.repository.BrandRepository;
import com.hatrongtan99.app.services.IBrandService;
import com.hatrongtan99.app.services.IMediaService;
import com.hatrongtan99.exception.DuplicatedException;
import com.hatrongtan99.exception.NotFoundException;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService implements IBrandService {

    private final IMediaService mediaService;
    private final BrandRepository brandRepository;


    @Override
    public BrandResponseDto create(BrandSaveDto brand) {
        boolean existBrandSlug = this.checkBrandSlugExist(brand.slug());
        if (existBrandSlug) {
            throw new DuplicatedException("Slug " + brand.slug() + " already exist");
        }
        Long idThumbnail = this.mediaService.saveFile(brand.brandThumbnail());
        BrandEntity newBrand = BrandEntity.builder()
                .name(brand.name())
                .slug(brand.slug())
                .thumbnailId(idThumbnail)
                .build();

        this.brandRepository.save(newBrand);
        return mapToDto(newBrand);
    }

    @Override
    public BrandResponseDto getBrandById(Long id) {
        BrandEntity brand = this.brandRepository.findById(id).orElseThrow(() -> new NotFoundException("brand not found"));
        return mapToDto(brand);
    }

    @Override
    public List<BrandResponseDto> getBrands() {
        return this.brandRepository.findAll().stream().map(BrandService::mapToDto).toList();
    }

    @Override
    @Transactional
    public BrandResponseDto update(Long id, BrandUpdateDto brandUpdate) {
        boolean existBrandSlug = this.checkBrandSlugExist(brandUpdate.slug());
        BrandEntity existBrand = this.brandRepository.findById(id).orElseThrow(() -> new NotFoundException("brand not found"));
        if (existBrandSlug && !existBrand.getSlug().equals(brandUpdate.slug())) {
            throw new DuplicatedException("Slug " + brandUpdate.slug() + " already exist");
        }
        existBrand.setName(brandUpdate.name());
        existBrand.setSlug(brandUpdate.slug());
        if (brandUpdate.brandThumbnail() != null) {
            Long newThumbnailId = this.mediaService.saveFile(brandUpdate.brandThumbnail());
            existBrand.setThumbnailId(newThumbnailId);
        }
       this.brandRepository.save(existBrand);
        return mapToDto(existBrand);
    }

    private boolean checkBrandSlugExist(String slug) {
        return this.brandRepository.existsBySlug(slug);
    }

    private static BrandResponseDto mapToDto(BrandEntity brand) {
        return BrandResponseDto.builder()
                .id(brand.getId())
                .thumbnailId(brand.getThumbnailId())
                .name(brand.getName())
                .slug(brand.getSlug())
                .isActive(brand.isActive())
                .build();
    }

}
