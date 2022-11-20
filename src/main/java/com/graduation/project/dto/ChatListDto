package com.graduation.project.dto;

import com.graduation.project.domain.ChatRoom;
import com.graduation.project.domain.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Data
@NoArgsConstructor
public class ChatListDto {
    private Long room_id;
    private String recentMessage;
    private LocalDate date;
    private LocalTime time;
    private Long opponent_id;
    private String opponent_nickname;
    private Long post_id;
    private Long comment_id;

    public ChatListDto(Long userId, ChatRoom chatRoom) {
        this.room_id = chatRoom.getId();
        Post post = chatRoom.getPost();
        if (post != null) {
            this.post_id = post.getId();
        }
        this.recentMessage = chatRoom.getMessage();
        this.date = chatRoom.getDate();
        this.time = chatRoom.getTime();
        if (!Objects.equals(chatRoom.getSender().getId(), userId)) {
            this.opponent_id = chatRoom.getSender().getId();
            this.opponent_nickname = chatRoom.getSender().getNickname();
        } else {
            this.opponent_id = chatRoom.getReceiver().getId();
            this.opponent_nickname = chatRoom.getReceiver().getNickname();
        }
        if (chatRoom.getComment() != null) {
            this.comment_id = chatRoom.getComment().getId();
        }
    }
}
