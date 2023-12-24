package com.hatrongtan99.app.services;

import com.hatrongtan99.app.dto.brandDto.BrandSaveDto;
import com.hatrongtan99.app.dto.brandDto.BrandUpdateDto;
import com.hatrongtan99.app.entity.BrandEntity;

import java.util.List;

public interface IBrandService {
    BrandEntity create(BrandSaveDto brand);

    BrandEntity getBrandById(Long id);

    List<BrandEntity> getBrands();

    BrandEntity update(Long id, BrandUpdateDto brand);

}
