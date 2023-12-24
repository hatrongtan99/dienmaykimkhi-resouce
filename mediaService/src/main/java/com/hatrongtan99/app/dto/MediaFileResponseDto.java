package com.hatrongtan99.app.dto;

import com.hatrongtan99.app.entity.MediaEntity;
import com.hatrongtan99.enumeration.media.FileType;
import org.springframework.web.util.UriComponentsBuilder;

public record MediaFileResponseDto(
        Long id,
        String caption,
        String filename,
        FileType fileType,
        String mediaType,
        String url
) {
    public static MediaFileResponseDto mapToDto(MediaEntity mediaEntity) {
        String url = mediaEntity.getUrl();
        if (url == null) {
            url = UriComponentsBuilder.fromUriString("http://localhost:8083")
                    .path("/medias/file").path("/" + mediaEntity.getId())
                    .build().toUriString();
        }
        return new MediaFileResponseDto(
                mediaEntity.getId(),
                mediaEntity.getCaption(),
                mediaEntity.getFilename(),
                mediaEntity.getFileType(),
                mediaEntity.getMediaType(),
                url
        );
    }
}
