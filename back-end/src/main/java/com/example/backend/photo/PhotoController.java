package com.example.backend.photo;

import com.example.backend.common.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

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
    public CommonResponse listAllPhotos() {
        var photoList = photoService.listAllPhotos();
        var response = PhotoDto.of(photoList);
        return CommonResponse.success(response);
    }

    @GetMapping("/photos/{newFilename}")
    public ResponseEntity<Resource> listImage(@PathVariable String newFilename) {
        Resource resource = new FileSystemResource(uploadPath + newFilename);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
}
