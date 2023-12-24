package com.hatrongtan99.app.serivce.impl;

import com.hatrongtan99.app.dto.MediaSaveDto;
import com.hatrongtan99.app.entity.MediaEntity;
import com.hatrongtan99.app.exception.NotFoundException;
import com.hatrongtan99.app.repository.MediaRepository;
import com.hatrongtan99.app.serivce.IMediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MediaService implements IMediaService {
    private final MediaRepository mediaRepository;

    @Override
    public MediaEntity saveMedia(MediaSaveDto mediaSaveDto)  {
        MediaEntity media = MediaEntity.builder()
                .caption(mediaSaveDto.caption())
                .fileType(mediaSaveDto.fileType())
                .build();

        if (mediaSaveDto.filename() == null || mediaSaveDto.filename().trim().isEmpty()) {
            if (mediaSaveDto.file() != null) {
                media.setFilename(mediaSaveDto.file().getOriginalFilename());
            } else {
                media.setFilename(UUID.randomUUID().toString().replace("-", " "));
            }
        } else {
            media.setFilename(mediaSaveDto.filename());
        }

        if (mediaSaveDto.url() != null) {
            media.setMediaType(MediaType.TEXT_PLAIN_VALUE);
            media.setUrl(media.getUrl());
        } else if (mediaSaveDto.file() != null) {
            try {
                MediaType mediaType = MediaType.valueOf(Objects.requireNonNull(mediaSaveDto.file().getContentType()));
                media.setData(mediaSaveDto.file().getBytes());
                media.setMediaType(mediaType.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {
            throw new RuntimeException("can't save file");
        }

        return this.mediaRepository.save(media);
    }

    @Override
    public MediaEntity getMediaById(Long id) {
        return this.mediaRepository.findById(id).orElseThrow(() -> new NotFoundException("File not found"));
    }

    @Override
    public void removeMedia(Long id) {
        MediaEntity media = this.mediaRepository.findById(id).orElseThrow(() -> new NotFoundException("File not found"));
        this.mediaRepository.delete(media);
    }
}
