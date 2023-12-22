package com.hatrongtan99.app.services.impl;

import com.hatrongtan99.app.dto.productAttributeDto.AttributeGetListDto;
import com.hatrongtan99.app.dto.productAttributeDto.AttributeSaveDto;
import com.hatrongtan99.app.dto.productAttributeDto.AttributeUpdateDto;
import com.hatrongtan99.app.entity.ProductAttributeEntity;
import com.hatrongtan99.app.repository.ProductAttributeRepository;
import com.hatrongtan99.app.services.IProductAttributeService;
import com.hatrongtan99.exception.DuplicatedException;
import com.hatrongtan99.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductAttributeService implements IProductAttributeService {

    private final ProductAttributeRepository attributeRepository;

    @Override
    public Long createAttribute(AttributeSaveDto attribute) {
        Optional<ProductAttributeEntity> exitByName = this.attributeRepository.findByName(attribute.name());
        if (exitByName.isPresent()) {
            throw new DuplicatedException("Attribute name " + attribute.name() + " already exist");
        }
        var newAttribute = ProductAttributeEntity.builder().name(attribute.name()).build();
        return this.attributeRepository.save(newAttribute).getId();
    }

    @Override
    public Long updateAttribute(AttributeUpdateDto attribute) {
        ProductAttributeEntity exitByName = this.attributeRepository.findByName(attribute.name())
                .orElseThrow(() -> new NotFoundException("attribute not found"));
        exitByName.setName(attribute.name());
        this.attributeRepository.save(exitByName);
        return exitByName.getId();
    }

    @Override
    public List<AttributeGetListDto> getAttributes() {
        List<ProductAttributeEntity> listAttribute = this.attributeRepository.findAll();
        return listAttribute.stream().map(att -> new AttributeGetListDto(att.getId(), att.getName(), att.isActive())).toList();
    }
}
