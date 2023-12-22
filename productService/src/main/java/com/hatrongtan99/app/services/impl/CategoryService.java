package com.hatrongtan99.app.services.impl;

import com.hatrongtan99.app.dto.categoryDto.CategoryResponseDto;
import com.hatrongtan99.app.dto.categoryDto.CategorySaveDto;
import com.hatrongtan99.app.dto.categoryDto.CategoryUpdateDto;
import com.hatrongtan99.app.dto.categoryDto.CategoryWithPageDto;
import com.hatrongtan99.app.dto.paginationDto.MetadataDto;
import com.hatrongtan99.app.entity.CategoryEntity;
import com.hatrongtan99.app.repository.CategoryRepository;
import com.hatrongtan99.app.services.ICategoryService;
import com.hatrongtan99.app.services.IMediaService;
import com.hatrongtan99.exception.BadRequestException;
import com.hatrongtan99.exception.DuplicatedException;
import com.hatrongtan99.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final IMediaService mediaService;
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDto createCategory(CategorySaveDto category) {
        boolean existBySlug = this.checkExistCategorySlug(category.slug());
        if (existBySlug) {
            throw new DuplicatedException("Slug category: " + category.slug() + " already exist");
        }
        Long idThumbnail = this.mediaService.saveFile(category.thumbnail());
        CategoryEntity newCate = CategoryEntity.builder()
                .name(category.name())
                .slug(category.slug())
                .description(category.description())
                .thumbnailId(idThumbnail)
                .build();
        if (category.parentId() != null) {
            CategoryEntity parentCate = this.categoryRepository.findById(category.parentId())
                    .orElseThrow(() -> new NotFoundException("parent category not found"));
            newCate.setParentId(parentCate);
        }
        newCate = this.categoryRepository.saveAndFlush(newCate);
        return mapToDto(newCate);
    }

    @Override
    public CategoryResponseDto updateCategory(Long id, CategoryUpdateDto category) {
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
        if (category.thumbnail() != null) {
            Long newIdThumbnail = this.mediaService.saveFile(category.thumbnail());
            existCategory.setThumbnailId(newIdThumbnail);
        }
        this.categoryRepository.saveAndFlush(existCategory);
        return mapToDto(existCategory);
    }

    @Override
    public CategoryWithPageDto getCategoryWithPage(int pageNumber, int pageLimit) {
        Pageable pageable = PageRequest.of(pageNumber, pageLimit);
        List<CategoryResponseDto> listCateResponse = new ArrayList<>();
        Page<CategoryEntity> pageCate = this.categoryRepository.findAll(pageable);
        for (CategoryEntity cate : pageCate.getContent()) {
            listCateResponse.add(mapToDto(cate));
        }
        MetadataDto metadataPage = new MetadataDto(
                pageCate.getNumber(),
                pageCate.getSize(),
                pageCate.getTotalPages(),
                (int) pageCate.getTotalElements()
        );
        return new CategoryWithPageDto(metadataPage, listCateResponse);
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
    private static CategoryResponseDto mapToDto(CategoryEntity category) {
        return new CategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getSlug(),
                category.getDescription(),
                category.getThumbnailId(),
                category.getParentId() == null ? null : category.getParentId().getId(),
                category.isActive()
        );
    }

}
