package com.utez.integradora.chat;


import com.utez.integradora.entity.Channel;
import com.utez.integradora.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "message")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserEntity sender;

    @ManyToOne
    private UserEntity receiver;

    @ManyToOne
    private Channel channel;

    private String message;
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private Status status;

    public ChatMessage(UserEntity sender, UserEntity receiver, Channel channel, String message, Status status) {
    }

    // Getters y setters
}

