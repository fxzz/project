package com.example.backend.photo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class PhotoDto {

    @Getter
    @Setter
    @ToString
    public static class RegisterPhotoRequest {
        @NotNull(message = "title 는 필수값입니다")
        private String title;

        @NotBlank(message = "content 는 필수값입니다")
        private String content;
    }
}
