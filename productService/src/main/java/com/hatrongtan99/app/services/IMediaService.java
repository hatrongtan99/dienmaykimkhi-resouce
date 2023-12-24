package com.hatrongtan99.app.services;

import com.hatrongtan99.app.dto.mediaDto.MediaResponseDto;

public interface IMediaService {
    MediaResponseDto getFile(Long id);
}
