package com.hatrongtan99.app.services.impl;

import com.hatrongtan99.app.dto.categoryDto.CategorySaveDto;
import com.hatrongtan99.app.dto.categoryDto.CategoryUpdateDto;
import com.hatrongtan99.app.entity.CategoryEntity;
import com.hatrongtan99.app.repository.CategoryRepository;
import com.hatrongtan99.app.services.ICategoryService;
import com.hatrongtan99.app.exception.BadRequestException;
import com.hatrongtan99.app.exception.DuplicatedException;
import com.hatrongtan99.app.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryEntity createCategory(CategorySaveDto category) {
        boolean existBySlug = this.checkExistCategorySlug(category.slug());
        if (existBySlug) {
            throw new DuplicatedException("Slug category: " + category.slug() + " already exist");
        }
        CategoryEntity newCate = CategoryEntity.builder()
                .name(category.name())
                .slug(category.slug())
                .description(category.description())
                .thumbnailId(category.thumbnailId())
                .build();
        if (category.parentId() != null) {
            CategoryEntity parentCate = this.categoryRepository.findById(category.parentId())
                    .orElseThrow(() -> new NotFoundException("parent category not found"));
            newCate.setParentId(parentCate);
        }
        return this.categoryRepository.saveAndFlush(newCate);
    }

    @Override
    public CategoryEntity updateCategory(Long id, CategoryUpdateDto category) {
        boolean existCategorySlug = this.checkExistCategorySlug(category.slug());
        CategoryEntity existCategory = this.categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("category not found"));
        if (existCategorySlug && !existCategory.getSlug().equals(category.slug())) {
            throw new DuplicatedException("Slug category " + category.name() + " already exist");
        }
        existCategory.setName(category.name());
        existCategory.setSlug(category.slug());
        existCategory.setDescription(category.description());
        if (category.parentId() == null) {
            existCategory.setParentId(null);
        } else {
            CategoryEntity parentCate = this.validateParentCategory(id, category.parentId());

            existCategory.setParentId(parentCate);
        }
        if (category.thumbnailId() != null) {
            existCategory.setThumbnailId(category.thumbnailId());
        }
        return this.categoryRepository.saveAndFlush(existCategory);
    }

    @Override
    public Page<CategoryEntity> getCategoryWithPage(int pageNumber, int pageLimit) {
        Pageable pageable = PageRequest.of(pageNumber, pageLimit);
        return this.categoryRepository.findAll(pageable);
    }

    @Override
    public Page<CategoryEntity> getAllParentCategory(int pageNumber, int pageLimit) {
        Pageable pageable = PageRequest.of(pageNumber, pageLimit);
        return this.categoryRepository.findByParentIdIsNull(pageable);
    }

    @Override
    public Page<CategoryEntity> getAllChildCategory(Long id, int pageNumber, int pageLimit) {
        Pageable pageable = PageRequest.of(pageNumber, pageLimit);
        return this.categoryRepository.findAllChildByParentId(id, pageable);
    }

    private boolean checkExistCategorySlug(String slug) {
        return this.categoryRepository.existsBySlug(slug);
    }

    private CategoryEntity validateParentCategory(Long currentId, Long parentId) {
        if (currentId.equals(parentId)) {
            throw new BadRequestException("Parent category cant not be itself");
        }

        return this.categoryRepository.findById(parentId)
                .orElseThrow(() -> new NotFoundException("category not found"));
    }

}
