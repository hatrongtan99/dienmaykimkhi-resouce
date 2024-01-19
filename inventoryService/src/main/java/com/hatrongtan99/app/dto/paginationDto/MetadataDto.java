package com.hatrongtan99.app.dto.paginationDto;

import org.springframework.data.domain.Page;

public record MetadataDto(
        int page,
        int pageSize,
        int pageCount,
        int totalCount
) {
    public static <T>MetadataDto mapToDto(Page<T> page) {
        return new MetadataDto(
                page.getNumber(),
                page.getNumberOfElements(),
                page.getTotalPages(),
                (int) page.getTotalElements()
        );
    }
}
