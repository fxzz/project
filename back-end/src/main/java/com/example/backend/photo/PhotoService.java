package com.example.backend.photo;

import java.io.IOException;

public interface PhotoService {
    void registerPhotos(PhotoDto.RegisterPhotoRequest request) throws IOException;

}
