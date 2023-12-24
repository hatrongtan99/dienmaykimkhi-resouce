package com.hatrongtan99.app.controller;

import com.hatrongtan99.app.dto.categoryDto.CategoryResponseDto;
import com.hatrongtan99.app.dto.categoryDto.CategorySaveDto;
import com.hatrongtan99.app.dto.categoryDto.CategoryUpdateDto;
import com.hatrongtan99.app.dto.categoryDto.CategoryWithPageDto;
import com.hatrongtan99.app.dto.paginationDto.MetadataDto;
import com.hatrongtan99.app.entity.CategoryEntity;
import com.hatrongtan99.app.services.ICategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/bff-admin/categories", "/bff-customer/categories"})
@AllArgsConstructor
public class CategoryController {
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<CategoryWithPageDto> getCategories(
            @RequestParam(name = "page", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageLimit", required = false, defaultValue = "5") int pageLimit
    ) {
        Page<CategoryEntity> pageCategory = this.categoryService.getCategoryWithPage(pageNumber, pageLimit);

        MetadataDto metadataPage = new MetadataDto(
                pageCategory.getNumber(),
                pageCategory.getSize(),
                pageCategory.getTotalPages(),
                (int) pageCategory.getTotalElements()
        );
        List<CategoryResponseDto> records = pageCategory.getContent().stream().map(CategoryResponseDto::mapToDto).toList();
        return ResponseEntity.ok(new CategoryWithPageDto(records, metadataPage));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CategoryResponseDto> createCategory(@Valid @RequestBody CategorySaveDto category) {
        CategoryEntity newCategory = this.categoryService.createCategory(category);

        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryResponseDto.mapToDto(newCategory));
    }

    @PutMapping({"/{id}"})
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CategoryResponseDto> updateCate(
            @PathVariable("id") Long id,
            @Valid @RequestBody CategoryUpdateDto category
    ) {
        CategoryEntity categoryUpdated = this.categoryService.updateCategory(id, category);
        return ResponseEntity.ok(CategoryResponseDto.mapToDto(categoryUpdated));
    }

}
