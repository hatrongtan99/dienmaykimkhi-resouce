package com.hatrongtan99.app.services.impl;

import com.hatrongtan99.app.dto.mediaDto.MediaResponseDto;
import com.hatrongtan99.app.dto.mediaDto.MediaSaveDto;
import com.hatrongtan99.app.services.IMediaService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class MediaService implements IMediaService {

    private final WebClient webClient;

    @Override
    public Long saveFile(MediaSaveDto file) {
        return null;
    }

    @Override
    public MediaResponseDto getFile(Long id) {
        return null;
    }
}
