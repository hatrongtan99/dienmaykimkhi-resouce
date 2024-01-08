package com.hatrongtan99.app.services.impl;

import com.hatrongtan99.app.dto.brandDto.BrandSaveDto;
import com.hatrongtan99.app.dto.brandDto.BrandUpdateDto;
import com.hatrongtan99.app.entity.BrandEntity;
import com.hatrongtan99.app.repository.BrandRepository;
import com.hatrongtan99.app.services.IBrandService;
import com.hatrongtan99.app.exception.DuplicatedException;
import com.hatrongtan99.app.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService implements IBrandService {

    private final BrandRepository brandRepository;

    @Override
    public BrandEntity create(BrandSaveDto brand) {
        boolean existBrandSlug = this.checkBrandSlugExist(brand.slug());
        if (existBrandSlug) {
            throw new DuplicatedException("Slug " + brand.slug() + " already exist");
        }
        BrandEntity newBrand = BrandEntity.builder()
                .name(brand.name())
                .slug(brand.slug())
                .thumbnailId(brand.thumbnailId())
                .build();

        return this.brandRepository.save(newBrand);
    }

    @Override
    public BrandEntity getBrandById(Long id) {
        return this.brandRepository.findById(id).orElseThrow(() -> new NotFoundException("brand not found"));
    }

    @Override
    public List<BrandEntity> getBrands() {
        return this.brandRepository.findAll();
    }

    @Override
    @Transactional
    public BrandEntity update(Long id, BrandUpdateDto brandUpdate) {
        boolean existBrandSlug = this.checkBrandSlugExist(brandUpdate.slug());
        BrandEntity existBrand = this.brandRepository.findById(id).orElseThrow(() -> new NotFoundException("brand not found"));
        if (existBrandSlug && !existBrand.getSlug().equals(brandUpdate.slug())) {
            throw new DuplicatedException("Slug " + brandUpdate.slug() + " already exist");
        }
        existBrand.setName(brandUpdate.name());
        existBrand.setSlug(brandUpdate.slug());
        if (brandUpdate.brandThumbnail() != null) {
            existBrand.setThumbnailId(brandUpdate.brandThumbnail());
        }
      return this.brandRepository.save(existBrand);
    }

    private boolean checkBrandSlugExist(String slug) {
        return this.brandRepository.existsBySlug(slug);
    }

    @Override
    public BrandEntity findBySlug(String slug) {
        return this.brandRepository.findBySlug(slug).orElseThrow(() -> new NotFoundException("Brand with slug: " + slug + " not found"));
    }
}
