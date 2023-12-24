package com.hatrongtan99.app.serivce;

import com.hatrongtan99.app.dto.MediaSaveDto;
import com.hatrongtan99.app.entity.MediaEntity;


public interface IMediaService {
    MediaEntity saveMedia(MediaSaveDto mediaSaveDto) ;

    MediaEntity getMediaById(Long id);

    void removeMedia(Long id);
}
