package com.utez.integradora.entity;

import com.utez.integradora.chat.Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Message {
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

    // Getters y setters
}
