package com.example.backend.photo.cursor;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CursorDto {
   private Long photoId;
   private String title;
   private String content;
   private Long accountId;
   private String newFilename;
   private String filename;

}
