package com.graduation.project.service;

import com.graduation.project.domain.*;
import com.graduation.project.dto.ChatDto;
import com.graduation.project.dto.ChatListDto;
import com.graduation.project.dto.CreateChatRequest;
import com.graduation.project.dto.CreateChatRoomRequest;
import com.graduation.project.error.ChatErrorResult;
import com.graduation.project.error.ChatException;
import com.graduation.project.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final ChatRepository chatRepository;
    private final ChatRoomRepository chatRoomRepository;

    public Long saveChat(Long userId, CreateChatRequest request) {
        if (userId == null) {
            throw new ChatException(ChatErrorResult.UNAUTHORIZED_USER_ACCESS);
        }

        User sendUser = userRepository.findById(userId)
                .orElseThrow(() -> new ChatException(ChatErrorResult.USER_NOT_EXIST));

        User receiveUser;

        Long post_id = request.getPost_id();
        Long comment_id = request.getComment_id();

        Post findPost = postRepository.findById(post_id)
                .orElseThrow(() -> new ChatException(ChatErrorResult.POST_NOT_EXIST));

        if (request.getComment_id() != null) {
            Comment findComment = commentRepository.findById(comment_id)
                    .orElseThrow(() -> new ChatException(ChatErrorResult.COMMENT_NOT_EXIST));
            receiveUser = findComment.getUser();
            if (receiveUser == null) {
                throw new ChatException(ChatErrorResult.COMMENT_NOT_EXIST);
            }
        } else {
            receiveUser = findPost.getUser();
        }

        if (Objects.equals(userId, receiveUser.getId())) {
            throw new ChatException(ChatErrorResult.NO_SEND_TO_SELF);
        }

        Chat chat = Chat.builder()
                .message(request.getMessage())
                .receiver(receiveUser)
                .sender(sendUser)
                .date(LocalDate.now())
                .time(LocalTime.now())
                .build();

        ChatRoom chatRoom1 = validateChatRoom(sendUser, receiveUser, comment_id, findPost, chat);
        ChatRoom chatRoom2 = validateChatRoom(sendUser, receiveUser, comment_id, findPost, chat);
        chatRoom1.updatePartnerId(chatRoom2.getId());
        chatRoom2.updatePartnerId(chatRoom1.getId());

        Chat savedChat = chatRepository.save(chat);
        return savedChat.getId();
    }
    private ChatRoom validateChatRoom(User sendUser, User receiveUser, Long comment_id, Post post, Chat chat) {
        ChatRoom findRoom = chatRoomRepository.findByReceiverAndSenderAndPost(
                receiveUser, sendUser, post
        ).orElse(null);
        if (findRoom == null) {
            ChatRoom chatRoom = ChatRoom.builder()
                    .receiver(receiveUser)
                    .sender(sendUser)
                    .post(post)
                    .chatList(new ArrayList<>())
                    .build();
            if (comment_id != null) {
                Comment findComment = commentRepository.getById(comment_id);
                chatRoom.updateComment(findComment);
            }
            chatRoomRepository.save(chatRoom);
            chat.updateChatRoom(chatRoom);
            return chatRoom;
        } else {
            chat.updateChatRoom(findRoom);
            return findRoom;
        }
    }

    public Long saveChatInRoom(Long userId, CreateChatRoomRequest request) {
        if (userId == null) {
            throw new ChatException(ChatErrorResult.UNAUTHORIZED_USER_ACCESS);
        }

        ChatRoom findRoom = chatRoomRepository.findById(request.getRoom_id())
                .orElseThrow(() -> new ChatException(ChatErrorResult.ROOM_NOT_EXIST));

        if (findRoom.getReceiver() == null || findRoom.getSender() == null) {
            throw new ChatException(ChatErrorResult.WITHDRAWN_MEMBER);
        }

        Long partnerUserId = findRoom.getSender().getId();
        if (Objects.equals(partnerUserId, userId)) {
            partnerUserId = findRoom.getReceiver().getId();
        }

        User sendUser = userRepository.getById(userId);
        User receiveUser = userRepository.getById(partnerUserId);
        Chat chat = Chat.builder()
                .message(request.getMessage())
                .receiver(receiveUser)
                .sender(sendUser)
                .date(LocalDate.now())
                .time(LocalTime.now())
                .build();

        chat.updateChatRoom(findRoom);

        Chat savedChat = chatRepository.save(chat);

        return savedChat.getId();
    }

    public List<ChatListDto> findAll(Long userId) {
        List<ChatRoom> chatList = chatRoomRepository.findByUser(userId);
        return chatList.stream()
                .map(chatRoom -> new ChatListDto(userId, chatRoom))
                .collect(Collectors.toList());
    }

    public ChatDto findByOpponent(Long userId, Long roomId) {
        ChatRoom findRoom = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new ChatException(ChatErrorResult.ROOM_NOT_EXIST));

        List<Chat> chatList = chatRepository.findByChatRoom(roomId);

        List<ChatDto.ChatInfo> result = chatList.stream()
                .map(ChatDto.ChatInfo::new)
                .collect(Collectors.toList());
        return new ChatDto(userId, findRoom, result);
    }

    public Long deleteChatRoom(Long userId, Long roomId) {
        chatRoomRepository.findById(roomId)
                .ifPresent(cr -> {
                    if (cr.getReceiver() == null) {
                        throw new ChatException(ChatErrorResult.WITHDRAWN_MEMBER);
                    }
                    if (!Objects.equals(cr.getReceiver().getId(), userId)) {
                        throw new ChatException(ChatErrorResult.UNAUTHORIZED_USER_ACCESS);
                    }
                    if (cr.getPartner_id() != null) {
                        chatRoomRepository.findById(cr.getPartner_id())
                                .ifPresent(c -> c.updatePartnerId(null));
                    }
                });
        chatRoomRepository.deleteById(roomId);
        return roomId;
    }
}
