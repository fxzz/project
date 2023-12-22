package com.example.backend.chat;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatDto {

    private String sender;          //  메시지를 보낸 사용자
    private String message;         // 채팅 메시지의 내용
    private LocalDateTime timestamp; // 메시지를 보낸 시간
    private String roomId;          //  채팅방

}
