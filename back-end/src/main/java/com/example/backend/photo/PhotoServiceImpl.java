package com.example.backend.photo;

import com.example.backend.common.util.FileExtensionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final PhotoMapper photoMapper;

    @Value("${uploadPath}")
    private String uploadPath;


    @Transactional
    @Override
    public void registerPhotos(PhotoDto.RegisterPhotoRequest request) throws IOException {

        String originalFilename = request.getOriginalFilename();

        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            if (FileExtensionUtils.isAllowedExtension(fileExtension)) {
                String newFilename = UUID.randomUUID() + "." + fileExtension;

                File upFile = new File(uploadPath, newFilename);
                request.getImage().transferTo(upFile);
                var photoDto= PhotoDto.of(request.getTitle(), request.getContent(), newFilename, originalFilename);

                photoMapper.insertPhoto(photoDto);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<PhotoDto> listAllPhotos() {
        return photoMapper.selectAllPhotos();
    }


}
