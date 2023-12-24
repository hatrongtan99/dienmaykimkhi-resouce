package com.hatrongtan99.app.services.impl;

import com.hatrongtan99.app.config.PropertiesConfig;
import com.hatrongtan99.app.dto.mediaDto.MediaResponseDto;
import com.hatrongtan99.app.services.IMediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class MediaService implements IMediaService {

    private final WebClient webClient;
    private final PropertiesConfig propertiesConfig;
    @Override
    public MediaResponseDto getFile(Long id) {
        final URI uri = UriComponentsBuilder.fromHttpUrl(propertiesConfig.getMediaUrl()).path("/medias/{id}").buildAndExpand(id).toUri();
        return this.webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(MediaResponseDto.class)
                .block();
    }
}
