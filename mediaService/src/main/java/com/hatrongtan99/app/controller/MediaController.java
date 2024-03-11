package com.hatrongtan99.app.controller;

import com.hatrongtan99.app.dto.MediaFileResponseDto;
import com.hatrongtan99.app.dto.MediaSaveDto;
import com.hatrongtan99.app.entity.MediaEntity;
import com.hatrongtan99.app.serivce.IMediaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medias")
@RequiredArgsConstructor
public class MediaController {
    private final IMediaService mediaService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<MediaFileResponseDto> uploadFile(
            @Valid MediaSaveDto mediaSaveDto
    ) {
        MediaEntity mediaEntity = this.mediaService.saveMedia(mediaSaveDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(MediaFileResponseDto.mapToDto(mediaEntity));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MediaFileResponseDto> getFileById(
            @PathVariable("id") Long id
    ) {
        MediaEntity mediaEntity = this.mediaService.getMediaById(id);

        return ResponseEntity.ok(MediaFileResponseDto.mapToDto(mediaEntity));
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<byte[]> getFile(
            @PathVariable("id") Long id
    ) {
        MediaEntity mediaEntity = this.mediaService.getMediaById(id);
        MediaType mediaType = MediaType.valueOf(mediaEntity.getMediaType());
        return ResponseEntity.ok().contentType(mediaType).body(mediaEntity.getData());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeFile(
            @PathVariable("id") Long id
    ) {
        this.mediaService.removeMedia(id);
        return ResponseEntity.noContent().build();
    }
}
