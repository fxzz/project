package com.example.backend.photo.cursor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CursorRequest {

    private Long photoId;
    private int size;
    public static final Long NONE_photoId = -1L;



    public Boolean hasPhotoId() {
        return photoId != null && !photoId.equals(NONE_photoId);
    }

    public  CursorRequest next(Long photoId) {
        return new CursorRequest(photoId, size);
    }

}
