package com.hatrongtan99.app.services.impl;

import com.hatrongtan99.app.dto.ProductAttributeItemDto.ProductAttributeItemResponseDto;
import com.hatrongtan99.app.dto.ProductAttributeItemDto.ProductAttributeItemSaveDto;
import com.hatrongtan99.app.dto.productAttributeDto.AttributeSaveDto;
import com.hatrongtan99.app.dto.productAttributeDto.AttributeUpdateDto;
import com.hatrongtan99.app.dto.productAttributeDto.ProductAttributeListItemResponseDto;
import com.hatrongtan99.app.dto.productAttributeDto.ProductAttributeListItemSaveDto;
import com.hatrongtan99.app.entity.ProductAttributeEntity;
import com.hatrongtan99.app.entity.ProductAttributeItemEntity;
import com.hatrongtan99.app.entity.ProductEntity;
import com.hatrongtan99.app.repository.ProductAttributeItemRepository;
import com.hatrongtan99.app.repository.ProductAttributeRepository;
import com.hatrongtan99.app.repository.ProductRepository;
import com.hatrongtan99.app.services.IProductAttributeService;
import com.hatrongtan99.app.exception.DuplicatedException;
import com.hatrongtan99.app.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductAttributeService implements IProductAttributeService {

    private final ProductAttributeRepository attributeRepository;
    private final ProductAttributeItemRepository attributeItemRepository;
    private final ProductRepository productRepository;

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
    public Long updateAttribute(Long id, AttributeUpdateDto attribute) {
        ProductAttributeEntity productAttribute = this.getById(id);
        if (this.attributeRepository.findByName(attribute.name()).isPresent()) {
            if (productAttribute.getName().equals(attribute.name())) {
                return productAttribute.getId();
            } else {
                throw new DuplicatedException("Product attribute with name: " + attribute.name() + " already exist");
            }
        }
        productAttribute.setName(attribute.name());
        return this.attributeRepository.save(productAttribute).getId();
    }

    @Override
    public void removeAttribute(Long id) {
        ProductAttributeEntity productAttribute = this.getById(id);
        productAttribute.setActive(false);
        this.attributeRepository.saveAndFlush(productAttribute);
    }

    @Override
    public List<ProductAttributeItemEntity> saveListAttributeItem(Long productId, Long attributeId, List<ProductAttributeItemSaveDto> items) {
        ProductEntity product = this.productRepository.findById(productId).orElseThrow(() -> new NotFoundException("product not found"));
        ProductAttributeEntity productAttribute = this.getById(attributeId);

        List<ProductAttributeItemEntity> listItems = items.stream()
                .map(i -> ProductAttributeItemEntity.builder()
                        .name(i.name())
                        .value(i.value())
                        .productAttributeId(productAttribute)
                        .productId(product).build()
                ).toList();
        return this.attributeItemRepository.saveAllAndFlush(listItems);
    }

    @Override
    public List<ProductAttributeListItemResponseDto> getAttributeByProductId(Long id) {
        Map<Long, ProductAttributeListItemResponseDto> mapResult = new HashMap<>();
        List<ProductAttributeItemEntity> attItems = this.attributeItemRepository.findByProductId(id);
        for (ProductAttributeItemEntity item : attItems) {
            Long productAttributeId = item.getProductAttributeId().getId();
            String nameProductAttribute = item.getProductAttributeId().getName();
            boolean isActiveProductAttribute = item.getProductAttributeId().isActive();
            if (!mapResult.containsKey(productAttributeId)) {
                mapResult.put(productAttributeId, new ProductAttributeListItemResponseDto(
                        productAttributeId,
                        nameProductAttribute,
                        isActiveProductAttribute,
                        new ArrayList<>()
                ));
            }
            mapResult.get(productAttributeId).items().add(new ProductAttributeItemResponseDto(item.getId(), item.getName(), item.getValue()));
        }
        return new ArrayList<>(mapResult.values());
    }

    private ProductAttributeEntity getById(Long id) {
        return this.attributeRepository.findById(id).orElseThrow(() -> new NotFoundException("product attribute: " + id + " notfound"));
    }


}
