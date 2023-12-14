package com.example.backend.photo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class PhotoDto {

    private String title;
    private String content;
    private String nickname;
    private String newFilename;
    private String filename;

    private PhotoDto(String title, String content, String nickname, String newFilename, String filename) {
        this.title = title;
        this.content = content;
        this.nickname = nickname;
        this.newFilename = newFilename;
        this.filename = filename;
    }

    public static PhotoDto of(String title, String content, String newFilename, String filename) {
        return new PhotoDto(title, content, getNickname(), newFilename, filename);
    }

    private static String getNickname() {
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
    }
}
