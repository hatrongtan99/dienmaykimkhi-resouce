package com.hatrongtan99.app.services;

import com.hatrongtan99.app.dto.descriotionDto.DescriptionUpdateDto;
import com.hatrongtan99.app.entity.DescriptionEntity;

public interface IDescriptionService {
    DescriptionEntity getDescriptionById(Long id);
    void updateDescriptionProduct(Long id, DescriptionUpdateDto descriptionUpdateDto);
}
