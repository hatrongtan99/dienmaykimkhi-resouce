package com.hatrongtan99.app.services;

import com.hatrongtan99.app.dto.brandDto.BrandResponseDto;
import com.hatrongtan99.app.dto.brandDto.BrandSaveDto;
import com.hatrongtan99.app.dto.brandDto.BrandUpdateDto;

import java.util.List;

public interface IBrandService {
    BrandResponseDto create(BrandSaveDto brand);

    BrandResponseDto getBrandById(Long id);

    List<BrandResponseDto> getBrands();

    BrandResponseDto update(Long id, BrandUpdateDto brand);

}
