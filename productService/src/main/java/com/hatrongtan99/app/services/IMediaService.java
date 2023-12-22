package com.hatrongtan99.app.services;

import com.hatrongtan99.app.dto.mediaDto.MediaResponseDto;
import com.hatrongtan99.app.dto.mediaDto.MediaSaveDto;

public interface IMediaService {
    Long saveFile(MediaSaveDto file);

    MediaResponseDto getFile(Long id);
}
