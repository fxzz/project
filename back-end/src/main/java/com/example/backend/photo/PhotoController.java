package com.example.backend.photo;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @PostMapping("/photos")
    public void registerPhotos(@Valid PhotoDto.RegisterPhotoRequest request) throws IOException {
        photoService.registerPhotos(request);
    }
}
