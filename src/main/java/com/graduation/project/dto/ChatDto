package com.graduation.project.dto;

import com.graduation.project.domain.Chat;
import com.graduation.project.domain.ChatRoom;
import com.graduation.project.domain.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Data
public class ChatDto {
    private Long room_id;
    private Long post_id;
    private Long comment_id;
    private List<ChatInfo> chatList;
    private Long my_id;
    private Long opponent_id;
    private String opponent_nickname;

    @Data
    @NoArgsConstructor
    public static class ChatInfo {
        private Long chat_id;
        private Long sender_id;
        private Long receiver_id;
        private LocalDate date;
        private LocalTime time;
        private String message;

        public ChatInfo(Chat chat) {
            this.chat_id = chat.getId();
            this.sender_id = chat.getSender().getId();
            this.receiver_id = chat.getReceiver().getId();
            this.date = chat.getDate();
            this.time = chat.getTime();
            this.message = chat.getMessage();
        }
    }

    public ChatDto(Long userId, ChatRoom chatRoom, List<ChatInfo> chatList) {
        Post post = chatRoom.getPost();
        if (post != null) {
            this.post_id = post.getId();
        }
        this.room_id = chatRoom.getId();
        if (chatRoom.getComment() != null) {
            this.comment_id = chatRoom.getComment().getId();
        }
        this.chatList = chatList;
        this.my_id = userId;
        if (!Objects.equals(chatRoom.getSender().getId(), userId)) {
            this.opponent_id = chatRoom.getSender().getId();
            this.opponent_nickname = chatRoom.getSender().getNickname();
        } else {
            this.opponent_id = chatRoom.getReceiver().getId();
            this.opponent_nickname = chatRoom.getReceiver().getNickname();
        }
    }
}
