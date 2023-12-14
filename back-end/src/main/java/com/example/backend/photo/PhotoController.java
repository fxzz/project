package com.example.backend.photo;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @PostMapping("/photos")
    public void registerPhotos(@RequestBody @Valid PhotoDto.RegisterPhotoRequest request) {
        System.out.println(request.getTitle());
        System.out.println(request.getContent());
    }
}
