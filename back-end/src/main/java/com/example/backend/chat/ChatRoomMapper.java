package com.example.backend.chat;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatRoomMapper {
   void insertChatRoom(ChatRoomDto chatRoomDto);
   boolean existsChatRoom(String roomName);
   List<ChatRoomDto> selectChatRoom(Long accountId);
}
