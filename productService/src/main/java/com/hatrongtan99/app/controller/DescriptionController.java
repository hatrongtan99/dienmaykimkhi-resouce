package com.hatrongtan99.app.controller;

import com.hatrongtan99.app.dto.descriotionDto.DescriptionResponseDto;
import com.hatrongtan99.app.dto.descriotionDto.DescriptionUpdateDto;
import com.hatrongtan99.app.entity.DescriptionEntity;
import com.hatrongtan99.app.services.IDescriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/bff-admin/description"})
@RequiredArgsConstructor
public class DescriptionController {
    private final IDescriptionService descriptionService;
    @GetMapping("/{id}")
    public ResponseEntity<DescriptionResponseDto> getDescription(
            @PathVariable("id") Long id
    ) {
        DescriptionEntity description = this.descriptionService.getDescriptionById(id);
        return ResponseEntity.ok(new DescriptionResponseDto(description.getId(), description.getContent()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDescription(
            @PathVariable("id") Long id,
            @Valid DescriptionUpdateDto descriptionUpdateDto
            ) {
        this.descriptionService.updateDescriptionProduct(id, descriptionUpdateDto);
        return ResponseEntity.noContent().build();
    }
}
