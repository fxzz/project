package com.example.backend.chat;

import com.example.backend.config.auth.AccountDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    // /app/chat/message
    // @MessageMapping("/chat/message")
    @MessageMapping("/chat/message/{roomId}")
    public void handleChatMessage(@DestinationVariable String roomId, ChatDto chatDto) {



        messagingTemplate.convertAndSend("/queue/chat/" + roomId, chatDto);

    }
    // sendingOperations.convertAndSend("/topic/chat/room/"+message.getRoomId(),message);

    // 클라이언트에서 "/app/chat/message"의 경로로 메시지를 보내는 요청을 하면
    // Controller 가 받아서 "queue/chat/{roomId}"를 구독하고 있는 클라이언트에게 메시지를 전달

    // @MessageMapping("/chat/message") 여기로 센드 하면
    // "/queue/chat/" + roomId, 여기를 get 하고 있는 사람한테 보여짐
}
