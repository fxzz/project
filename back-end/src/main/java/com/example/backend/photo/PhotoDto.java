package com.example.backend.photo;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
public class PhotoDto {

    private Long photoId;
    private String title;
    private String content;
    private Long accountId;
    private String newFilename;
    private String filename;
    private LocalDateTime createdAt;



    private PhotoDto(String title, String content, Long accountId, String newFilename, String filename) {
        this.title = title;
        this.content = content;
        this.accountId = accountId;
        this.newFilename = newFilename;
        this.filename = filename;
    }


    public static PhotoDto of(String title, String content, Long accountId, String newFilename, String filename) {
        return new PhotoDto(title, content, accountId, newFilename, filename);
    }






    @Getter
    @Setter
    @ToString
    public static class RegisterPhotoRequest {

        @NotBlank(message = "제목은 필수입니다")
        @Pattern(regexp = "^[가-힣ㄱ-ㅎㅏ-ㅣA-Za-z0-9~!#@?\\s]*$", message = "제목은 필수입니다")
        private String title;

        @Pattern(regexp = "^[가-힣ㄱ-ㅎㅏ-ㅣA-Za-z0-9~!#@?\\s]*$", message = "내용은 필수입니다")
        @NotBlank(message = "내용은 필수입니다")
        private String content;


        @NotNull(message = "이미지는 필수입니다")
        private MultipartFile image;


        public String getOriginalFilename() {
            return image.getOriginalFilename();
        }


    }
}
