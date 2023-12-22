package com.hatrongtan99.app.dto.mediaDto;

import com.hatrongtan99.enumeration.media.MediaType;

public record MediaResponseDto(
        Long id,
        String caption,
        String filename,
        MediaType mediaType,
        byte[] data,
        String url
) {

}
