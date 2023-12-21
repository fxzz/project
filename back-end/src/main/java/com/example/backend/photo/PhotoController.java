package com.example.backend.photo;

import com.example.backend.common.response.CommonResponse;


import com.example.backend.config.auth.AccountDetails;
import com.example.backend.photo.cursor.CursorDto;

import com.example.backend.photo.cursor.CursorResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;


    @Value("${uploadPath}")
    private String uploadPath;



    @PostMapping("/photos")
    public CommonResponse registerPhotos(@Valid PhotoDto.RegisterPhotoRequest request, @AuthenticationPrincipal AccountDetails accountDetails) {
        photoService.registerPhotos(request, uploadPath, accountDetails.getNickname());
        return CommonResponse.success("OK");
    }

    @GetMapping("/photos")
    public CommonResponse<CursorResponse<CursorDto>> getPhotoPage(@RequestParam(required = false) Long photoId, int size) {
        CursorResponse<CursorDto> page = photoService.getPage(photoId, size);
        return CommonResponse.success(page);
    }

    @GetMapping(value = "/photos/{photo-id}")
    public CommonResponse getDetailsPhoto(@PathVariable("photo-id") Long photoId) {
            PhotoDto photoDto = photoService.getDetailsPhoto(photoId);
        return CommonResponse.success(photoDto);
    }

    @GetMapping(value = "/file/{new-filename}")
    public ResponseEntity<Resource> listImage(@PathVariable("new-filename") String newFilename) {
        Resource resource = new FileSystemResource(uploadPath + newFilename);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }



}
