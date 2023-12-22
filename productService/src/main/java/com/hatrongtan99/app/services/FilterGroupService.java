package com.hatrongtan99.app.services;

import com.hatrongtan99.app.dto.filterGroupDto.FilterGroupResponseDto;
import com.hatrongtan99.app.dto.filterGroupDto.FilterGroupSaveOrUpdateDto;
import com.hatrongtan99.app.dto.productFilterDto.ProductFilterSaveOrUpdate;
import com.hatrongtan99.app.entity.filter.FilterGroup;
import com.hatrongtan99.app.entity.filter.ProductFilter;
import com.hatrongtan99.app.repository.filter.FilterGroupRepository;
import com.hatrongtan99.app.repository.filter.ProductFilterRepository;
import com.hatrongtan99.exception.DuplicatedException;
import com.hatrongtan99.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FilterGroupService {
    private final FilterGroupRepository filterGroupRepository;
    private final ProductFilterRepository productFilterRepository;

    public FilterGroupResponseDto createFilterGroup(FilterGroupSaveOrUpdateDto model) {
        this.validateFilterGroupByName(model.name());
        FilterGroup newFilterGroup = FilterGroup.builder()
                .name(model.name())
                .build();
        if (model.productFilters() != null) {
            setProductFilters(newFilterGroup, model.productFilters());
        }
        newFilterGroup = this.filterGroupRepository.saveAndFlush(newFilterGroup);
        return new FilterGroupResponseDto(newFilterGroup.getId(), newFilterGroup.getName());
    }

    @Transactional
    public FilterGroupResponseDto updateFilterGroup(Long id, FilterGroupSaveOrUpdateDto model) {
        boolean existFilterGroupName = this.filterGroupRepository.existsByName(model.name());
        FilterGroup existFilterGroup = this.filterGroupRepository.findById(id).orElseThrow(() -> new NotFoundException("Filter group not found"));
        if (existFilterGroupName && !existFilterGroup.getName().equals(model.name())){
            throw new DuplicatedException("Filter group " + model.name() + " already exist");
        }
        existFilterGroup.setName(model.name());
        if (model.productFilters() !=null) {
            this.setProductFilters(existFilterGroup, model.productFilters());
        }
        return new FilterGroupResponseDto(existFilterGroup.getId(), existFilterGroup.getName());
    }

    public void deleteFilterGroup(Long id) {
        FilterGroup existFilterGroup = this.filterGroupRepository.findById(id).orElseThrow(() -> new NotFoundException("Filter group not found"));
        existFilterGroup.setActive(false);
        this.filterGroupRepository.save(existFilterGroup);
    }

    private void validateFilterGroupByName( String name) {
        if (this.filterGroupRepository.existsByName(name)) {
            throw new DuplicatedException("Filter group " + name + " already exist");
        }
    }

    private void setProductFilters(FilterGroup filterGroup, List<ProductFilterSaveOrUpdate> productFilterSaveOrUpdates) {
        if (productFilterSaveOrUpdates.isEmpty()) {
            return;
        }
        List<ProductFilter> listSave = new ArrayList<>();
        List<ProductFilter> listUpdate = new ArrayList<>();
        for (ProductFilterSaveOrUpdate p : productFilterSaveOrUpdates) {
            if (p.id() == null) {
                listSave.add(ProductFilter.builder().name(p.name()).filterGroups(filterGroup).build());
            } else {
                listUpdate.add(ProductFilter.builder()
                        .id(p.id())
                        .name(p.name())
                        .build());
            }
        }
        if (filterGroup.getFilters() == null) {
            filterGroup.setFilters(new HashSet<>(listSave));
        } else {
            Set<ProductFilter> currentProductFilter = filterGroup.getFilters();
            List<ProductFilter> listDelete = currentProductFilter.stream().filter(p -> !listUpdate.contains(p)).toList();
            listDelete.forEach(filterGroup.getFilters()::remove);
            filterGroup.getFilters().addAll(listSave);
            filterGroup.getFilters().addAll(listUpdate); //
        }
    }
}
