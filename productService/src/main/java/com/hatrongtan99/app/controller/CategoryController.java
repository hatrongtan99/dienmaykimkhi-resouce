package com.hatrongtan99.app.controller;

import com.hatrongtan99.app.dto.categoryDto.*;
import com.hatrongtan99.app.dto.mediaDto.MediaResponseDto;
import com.hatrongtan99.app.dto.paginationDto.MetadataDto;
import com.hatrongtan99.app.entity.CategoryEntity;
import com.hatrongtan99.app.services.ICategoryService;
import com.hatrongtan99.app.services.IMediaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

@RestController
@RequestMapping({"/bff-admin/categories", "/bff-customer/categories"})
@AllArgsConstructor
public class CategoryController {
    private ICategoryService categoryService;
    private IMediaService mediaService;

    @GetMapping
    public ResponseEntity<CategoryWithPageDto> getCategories(
            @RequestParam(name = "page", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageLimit", required = false, defaultValue = "5") int pageLimit
    ) {
        Page<CategoryEntity> pageCategory = this.categoryService.getCategoryWithPage(pageNumber, pageLimit);
        MetadataDto metadataPage = MetadataDto.mapToDto(pageCategory);
        List<CategoryResponseDto> records = this.setImages(pageCategory, CategoryResponseDto::mapToDto);
        return ResponseEntity.ok(new CategoryWithPageDto(records, metadataPage));
    }

    @GetMapping("/parents")
    public ResponseEntity<CategoryParentWithPage> getAllParentCategories(
            @RequestParam(name = "page", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageLimit", required = false, defaultValue = "5") int pageLimit
    ) {
        Page<CategoryEntity> page = this.categoryService.getAllParentCategory(pageNumber, pageLimit);
        MetadataDto metadataPage = MetadataDto.mapToDto(page);
        List<CategoryResponseDto> records = this.setImages(page, CategoryResponseDto::mapToDto);
        return ResponseEntity.ok(new CategoryParentWithPage(records, metadataPage));
    }

    @GetMapping("/child/{id}")
    public ResponseEntity<CategoryChildWithPage> getAllChildCategory(
            @PathVariable("id") Long parentId,
            @RequestParam(name = "page", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageLimit", required = false, defaultValue = "5") int pageLimit
    ) {
        Page<CategoryEntity> page = this.categoryService.getAllChildCategory(parentId, pageNumber, pageLimit);
        MetadataDto metadataPage = MetadataDto.mapToDto(page);
        List<CategoryResponseDto> records = this.setImages(page, CategoryResponseDto::mapToDto);
        return ResponseEntity.ok(new CategoryChildWithPage(records, metadataPage));
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CategoryResponseDto> createCategory(@Valid @RequestBody CategorySaveDto category) {
        CategoryEntity newCategory = this.categoryService.createCategory(category);

        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryResponseDto.mapToDto(newCategory, null));
    }

    @PutMapping({"/{id}"})
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CategoryResponseDto> updateCate(
            @PathVariable("id") Long id,
            @Valid @RequestBody CategoryUpdateDto category
    ) {
        CategoryEntity categoryUpdated = this.categoryService.updateCategory(id, category);
        return ResponseEntity.ok(CategoryResponseDto.mapToDto(categoryUpdated, null));
    }

    public <T> List<T> setImages(Page<CategoryEntity> page, BiFunction<CategoryEntity, MediaResponseDto, T> biFunction) {

        List<T> result = new ArrayList<>();
        for (CategoryEntity category : page.getContent()) {
            if (category.getThumbnailId() != null) {
                MediaResponseDto media = this.mediaService.getFile(category.getThumbnailId());
                result.add(biFunction.apply(category, media));
            } else {
                result.add(biFunction.apply(category, null));
            }

        }
        return result;
    }

}
