package com.example.backend.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService{

    private final ChatRoomMapper chatRoomMapper;

    @Transactional
    @Override
    public void createChatRoom(ChatRoomDto chatRoomDto) {
        chatRoomMapper.insertChatRoom(chatRoomDto);
    }


    @Override
    public boolean existsChatRoom(String roomName) {
        return chatRoomMapper.existsChatRoom(roomName);
    }

    @Override
    public List<ChatRoomDto> getAccountChatRooms(Long accountId) {
        return chatRoomMapper.selectChatRoom(accountId);
    }


}
