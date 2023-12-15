package com.example.backend.photo;

import java.io.IOException;
import java.util.List;

public interface PhotoService {
    void registerPhotos(PhotoDto.RegisterPhotoRequest request) throws IOException;

    List<PhotoDto> listAllPhotos();
}
