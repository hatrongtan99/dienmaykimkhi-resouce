package com.hatrongtan99.app.services.impl;

import com.hatrongtan99.app.dto.descriotionDto.DescriptionUpdateDto;
import com.hatrongtan99.app.entity.DescriptionEntity;
import com.hatrongtan99.app.exception.NotFoundException;
import com.hatrongtan99.app.repository.DescriptionRepository;
import com.hatrongtan99.app.repository.ProductRepository;
import com.hatrongtan99.app.services.IDescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DescriptionService implements IDescriptionService {
    private final DescriptionRepository descriptionRepository;

    @Override
    public DescriptionEntity getDescriptionById(Long id) {
        return this.descriptionRepository.findById(id).orElseThrow(() -> new NotFoundException("description not found"));
    }

    @Override
    public void updateDescriptionProduct(Long id, DescriptionUpdateDto descriptionUpdateDto) {
        DescriptionEntity description = this.getDescriptionById(id);
        description.setContent(descriptionUpdateDto.content());
        this.descriptionRepository.saveAndFlush(description);
    }
}
