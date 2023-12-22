package com.hatrongtan99.app.services;

import com.hatrongtan99.app.dto.categoryDto.CategoryResponseDto;
import com.hatrongtan99.app.dto.categoryDto.CategorySaveDto;
import com.hatrongtan99.app.dto.categoryDto.CategoryUpdateDto;
import com.hatrongtan99.app.dto.categoryDto.CategoryWithPageDto;

public interface ICategoryService {

    CategoryResponseDto createCategory(CategorySaveDto category);
    CategoryResponseDto updateCategory(Long id, CategoryUpdateDto category);

    CategoryWithPageDto getCategoryWithPage(int pageNumber, int pageLimit);

//    Object getDetailCategory(Long id);
}
