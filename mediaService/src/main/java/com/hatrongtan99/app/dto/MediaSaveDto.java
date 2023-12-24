package com.hatrongtan99.app.dto;

import com.hatrongtan99.enumeration.media.FileType;
import org.springframework.web.multipart.MultipartFile;

public record MediaSaveDto(
        String caption,
        String filename,
        MultipartFile file,
        FileType fileType,
        String url

) {
}
