package com.hatrongtan99.app.controller;

import com.hatrongtan99.app.dto.categoryDto.CategoryResponseDto;
import com.hatrongtan99.app.dto.categoryDto.CategorySaveDto;
import com.hatrongtan99.app.dto.categoryDto.CategoryUpdateDto;
import com.hatrongtan99.app.dto.categoryDto.CategoryWithPageDto;
import com.hatrongtan99.app.services.ICategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(this.categoryService.getCategoryWithPage(pageNumber, pageLimit));
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CategoryResponseDto> createCategory(@Valid @RequestBody CategorySaveDto category) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.categoryService.createCategory(category));
    }

    @PutMapping({"/{id}"})
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CategoryResponseDto> updateCate(
            @PathVariable("id") Long id,
            @Valid @RequestBody CategoryUpdateDto category
    ) {
        return ResponseEntity.ok(this.categoryService.updateCategory(id, category));
    }

}
