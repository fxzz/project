package com.example.backend.photo.cursor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CursorResponse<T> {
    private CursorRequest nextCursorRequest;
    List<T> content;
}
