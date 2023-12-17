package com.example.backend.photo;

import com.example.backend.photo.cursor.CursorDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PhotoMapper {
   void insertPhoto(PhotoDto photoDto);


   List<CursorDto> selectFirstPage(Map map);
   List<CursorDto> selectNextPage(Map map);

}
