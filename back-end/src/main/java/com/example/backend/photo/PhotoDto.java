package com.example.backend.photo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PhotoDto implements Serializable {

    private Long photoId;
    private String title;
    private String content;
    private String nickname;
    private String newFilename;
    private String filename;
    private LocalDateTime createdAt;



    private PhotoDto(String title, String content, String nickname, String newFilename, String filename) {
        this.title = title;
        this.content = content;
        this.nickname = nickname;
        this.newFilename = newFilename;
        this.filename = filename;
    }


    public static PhotoDto of(String title, String content, String newFilename, String filename) {
        return new PhotoDto(title, content, getName(), newFilename, filename);
    }

    private static String getName() {
        return "익명";
    }




    @Getter
    @Setter
    @ToString
    public static class RegisterPhotoRequest {
        @NotNull(message = "title 는 필수값입니다")
        private String title;

        @NotBlank(message = "content 는 필수값입니다")
        private String content;

        @NotNull(message = "image 는 필수값입니다")
        private MultipartFile image;


        public String getOriginalFilename() {
            return image.getOriginalFilename();
        }
    }
}
