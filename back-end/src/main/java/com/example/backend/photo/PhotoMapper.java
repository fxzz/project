package com.example.backend.photo;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PhotoMapper {
   void insertPhoto(PhotoDto photoDto);
   List<PhotoDto> selectAllPhotos();
}
