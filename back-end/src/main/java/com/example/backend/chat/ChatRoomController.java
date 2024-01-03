package com.example.backend.chat;

import com.example.backend.common.response.CommonResponse;
import com.example.backend.config.auth.AccountDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    /**
     채팅에 /chat-rooms 를 같이 보내

     리엑트의 useState 에 "OK" 를 저장해
     있으면 /chat-rooms 를 패스
     */
    @PostMapping("/chat-rooms")
    public CommonResponse createChatRoom(@RequestBody ChatRoomDto chatRoomDto) {
        if (!chatRoomService.existsChatRoom(chatRoomDto.getRoomName())) {
            chatRoomService.createChatRoom(chatRoomDto);
        return CommonResponse.success("OK");
    }
        return CommonResponse.success("OK");
    }



    @GetMapping("/chat-rooms/{accountId}")
    public CommonResponse getAccountChatRooms(@PathVariable Long accountId, @AuthenticationPrincipal AccountDetails accountDetails) {
        if (!accountId.equals(accountDetails.getAccount().getAccountId())) {
            throw new RuntimeException("권한이 없습니다"); // 403 관련 에러 만들어야함
        }

        List<ChatRoomDto> chatRooms = chatRoomService.getAccountChatRooms(accountId);
        return CommonResponse.success(chatRooms);
    }
}
