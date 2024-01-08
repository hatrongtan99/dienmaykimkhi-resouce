package com.hatrongtan99.app.services;

import com.hatrongtan99.app.dto.categoryDto.CategorySaveDto;
import com.hatrongtan99.app.dto.categoryDto.CategoryUpdateDto;
import com.hatrongtan99.app.entity.CategoryEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICategoryService {
    CategoryEntity createCategory(CategorySaveDto category);
    CategoryEntity updateCategory(Long id, CategoryUpdateDto category);
    Page<CategoryEntity> getCategoryWithPage(int pageNumber, int pageLimit);
    Page<CategoryEntity> getAllParentCategory(int pageNumber, int pageLimit);

    Page<CategoryEntity> getAllChildCategory(Long id, int pageNumber, int pageLimit);

//    Object getDetailCategory(Long id);
}
