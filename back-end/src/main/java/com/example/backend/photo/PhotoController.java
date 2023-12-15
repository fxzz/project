package com.example.backend.photo;

import com.example.backend.common.response.CommonResponse;
import com.example.backend.photo.cursor.CursorDto;
import com.example.backend.photo.cursor.CursorRequest;
import com.example.backend.photo.cursor.CursorResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @Value("${uploadPath}")
    private String uploadPath;


    @PostMapping("/photos")
    public CommonResponse registerPhotos(@Valid PhotoDto.RegisterPhotoRequest request) throws IOException {
        photoService.registerPhotos(request);
        return CommonResponse.success("OK");
    }

    @GetMapping("/photos")
    public ResponseEntity<CursorResponse<CursorDto>> getPhotoPage(@RequestParam(required = false) Long photoId,
                                                                  @RequestParam int size) {
        CursorRequest cursorRequest = new CursorRequest(photoId, size);
        CursorResponse<CursorDto> page = photoService.getPage(cursorRequest);

        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/photos/{newFilename}")
    public ResponseEntity<Resource> listImage(@PathVariable String newFilename) {
        Resource resource = new FileSystemResource(uploadPath + newFilename);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }



}
