package com.graduation.project.controller;

import com.graduation.project.argumentResolver.Login;
import com.graduation.project.dto.ChatDto;
import com.graduation.project.dto.ChatListDto;
import com.graduation.project.dto.CreateChatRequest;
import com.graduation.project.dto.CreateChatRoomRequest;
import com.graduation.project.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatController {
    
    private final ChatService chatService;
    
    @PostMapping("/user/chat")
    public Long createChat(@Login Long userId, @RequestBody CreateChatRequest request) {
        return chatService.saveChat(userId, request);
    }
    
    @PostMapping("/user/chat/inRoom")
    public Long createChatInRoom(@Login Long userId, @RequestBody CreateChatRoomRequest request) {
        return chatService.saveChatInRoom(userId, request);
    }
    
    @GetMapping("/user/chats")
    public List<ChatListDto> findAll(@Login Long userId) {
        return chatService.findAll(userId);
    }
    
    @GetMapping("/user/chat/{roomId}")
    public ChatDto searchByPost(@Login Long userId, @PathVariable Long roomId) {
        return chatService.findByOpponent(userId, roomId);
    }
    
    @DeleteMapping("/user/chat/{roomId}")
    public Long deleteChatRoom(@Login Long userId, @PathVariable Long roomId) {
        return chatService.deleteChatRoom(userId, roomId);
    }
}
