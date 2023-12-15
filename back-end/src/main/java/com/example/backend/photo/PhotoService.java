package com.example.backend.photo;

import com.example.backend.photo.cursor.CursorDto;
import com.example.backend.photo.cursor.CursorRequest;
import com.example.backend.photo.cursor.CursorResponse;

import java.io.IOException;
import java.util.List;

public interface PhotoService {
    void registerPhotos(PhotoDto.RegisterPhotoRequest request) throws IOException;

    List<PhotoDto> listAllPhotos();

    CursorResponse<CursorDto> getPage(CursorRequest cursorRequest);

}
