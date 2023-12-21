package com.example.backend.photo;

import com.example.backend.common.exception.UploadException;
import com.example.backend.common.util.FileExtensionUtils;
import com.example.backend.photo.cursor.CursorDto;
import com.example.backend.photo.cursor.CursorRequest;
import com.example.backend.photo.cursor.CursorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final PhotoMapper photoMapper;



    @Transactional
    @Override
    public void registerPhotos(PhotoDto.RegisterPhotoRequest request, String uploadPath, String nickname) {


        String originalFilename = request.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

            if (FileExtensionUtils.isAllowedExtension(fileExtension)) {
                String newFilename = UUID.randomUUID() + "." + fileExtension;

                File upFile = new File(uploadPath, newFilename);

                try {
                    request.getImage().transferTo(upFile);
                } catch (IOException e) {
                    log.error("Error transferring image:", e);
                    throw new UploadException("업로드 중에 오류가 발생했습니다. 다시 시도해주세요.");
                }
                var photoDto= PhotoDto.of(request.getTitle(), request.getContent(),nickname ,newFilename, originalFilename);
                photoMapper.insertPhoto(photoDto);
        }
    }


    @Transactional(readOnly = true)
    @Override
    public PhotoDto getDetailsPhoto(Long photoId) {
        return photoMapper.selectPhoto(photoId);
    }


    @Transactional(readOnly = true)
    @Override
    public CursorResponse<CursorDto> getPage(Long photoId, int size) {

        CursorRequest cursorRequest = new CursorRequest(photoId, size);

        List<CursorDto> cursorDtos;

        if (cursorRequest.hasPhotoId()) {
            // 다음 페이지
            cursorDtos = selectNextPage(cursorRequest);
        } else {
            // 첫 페이지
            cursorDtos = selectFirstPage(cursorRequest);
        }

        // 현재 페이지의 최솟값을 찾아 다음 페이지의 시작점으로 설정
        Long nextPhotoId = cursorDtos.stream()
                .mapToLong(CursorDto::getPhotoId)
                .min()
                .orElse(CursorRequest.NONE_photoId);

        return new CursorResponse<>(cursorRequest.next(nextPhotoId), cursorDtos);
    }

    private List<CursorDto> selectFirstPage(CursorRequest cursorRequest) {
        Map<String, Object> map = new HashMap<>();
        map.put("size", cursorRequest.getSize());

        return photoMapper.selectFirstPage(map);
    }

    private List<CursorDto> selectNextPage(CursorRequest cursorRequest) {
        Map<String, Object> map = new HashMap<>();
        map.put("photoId", cursorRequest.getPhotoId());
        map.put("size", cursorRequest.getSize());

        return photoMapper.selectNextPage(map);
    }
}
