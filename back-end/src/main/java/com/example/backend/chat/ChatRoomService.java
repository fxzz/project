package com.example.backend.chat;

import java.util.List;

public interface ChatRoomService {
    void createChatRoom(ChatRoomDto chatRoomDto);
    boolean existsChatRoom(String roomName);


    List<ChatRoomDto> getAccountChatRooms(Long accountId);
}
