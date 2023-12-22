package com.hatrongtan99.app.services;

import com.hatrongtan99.app.dto.productAttributeDto.AttributeGetListDto;
import com.hatrongtan99.app.dto.productAttributeDto.AttributeSaveDto;
import com.hatrongtan99.app.dto.productAttributeDto.AttributeUpdateDto;

import java.util.List;

public interface IProductAttributeService {
    Long createAttribute(AttributeSaveDto attribute);
    Long updateAttribute(AttributeUpdateDto attribute);

    List<AttributeGetListDto> getAttributes();
}
