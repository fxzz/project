package com.example.backend.photo;

import com.example.backend.photo.cursor.CursorDto;

import com.example.backend.photo.cursor.CursorResponse;




public interface PhotoService {
    void registerPhotos(PhotoDto.RegisterPhotoRequest request, String uploadPath, Long accountId);


    CursorResponse<CursorDto> getPage(Long photoId, int size);

    PhotoDto getDetailsPhoto(Long photoId);
}
