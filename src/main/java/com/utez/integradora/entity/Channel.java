package com.utez.integradora.entity;

import jakarta.persistence.*;

@Entity
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserEntity user1;

    @ManyToOne
    private UserEntity user2;

    public Channel(UserEntity user1, UserEntity user2) {
    }

    // Getters y setters
}

