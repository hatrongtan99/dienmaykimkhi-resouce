package com.hatrongtan99.app.dto.mediaDto;

import com.hatrongtan99.enumeration.media.FileType;

public record MediaResponseDto(
        Long id,
        String caption,
        String filename,
        FileType fileType,
        String url
) {

}
