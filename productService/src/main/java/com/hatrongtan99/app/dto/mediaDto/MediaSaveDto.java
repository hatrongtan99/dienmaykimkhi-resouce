package com.hatrongtan99.app.dto.mediaDto;

import com.hatrongtan99.enumeration.media.MediaType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record MediaSaveDto(
        String caption,
        @NotNull
        @NotBlank
        String fileName,
        @NotNull
        MediaType mediaType,
        @NotNull
        MultipartFile data,
        String url

) {
}
