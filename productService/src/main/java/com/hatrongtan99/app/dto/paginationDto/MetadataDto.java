package com.hatrongtan99.app.dto.paginationDto;

public record MetadataDto(
        int page,
        int pageSize,
        int pageCount,
        int totalCount
) {
}
