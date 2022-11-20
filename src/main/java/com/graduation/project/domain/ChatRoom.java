package com.graduation.project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class ChatRoom extends BaseEntity{

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private String message;

    private Long partner_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private User receiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @OneToMany(mappedBy = "chatRoom")
    private List<Chat> chatList = new ArrayList<>();


    public void updateComment(Comment comment) {
        this.comment = comment;
    }
    public void updateMessage(String message) {
        this.message = message;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }
    public void updatePartnerId(Long partner_id) {
        this.partner_id = partner_id;
    }
}
