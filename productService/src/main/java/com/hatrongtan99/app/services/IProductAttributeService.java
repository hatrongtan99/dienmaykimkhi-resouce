package com.hatrongtan99.app.services;

import com.hatrongtan99.app.dto.ProductAttributeItemDto.ProductAttributeItemSaveDto;
import com.hatrongtan99.app.dto.productAttributeDto.AttributeSaveDto;
import com.hatrongtan99.app.dto.productAttributeDto.AttributeUpdateDto;
import com.hatrongtan99.app.dto.productAttributeDto.ProductAttributeListItemResponseDto;
import com.hatrongtan99.app.entity.ProductAttributeEntity;
import com.hatrongtan99.app.entity.ProductAttributeItemEntity;

import java.util.List;


public interface IProductAttributeService {
    Long createAttribute(AttributeSaveDto attribute);
    Long updateAttribute(Long id, AttributeUpdateDto attribute);
    void removeAttribute(Long id);
    List<ProductAttributeListItemResponseDto> getAttributeByProductId(Long id);
    List<ProductAttributeItemEntity> saveListAttributeItem(Long productId, Long attributeId, List<ProductAttributeItemSaveDto> items);
}
