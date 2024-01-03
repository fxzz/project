package com.example.backend.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoomDto {
   private Long ChatRoomId;
   private Long senderAccountId;
   private Long accountId;
   private String roomName;
   private String title;
}
