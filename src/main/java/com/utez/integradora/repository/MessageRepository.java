package com.utez.integradora.repository;

import com.utez.integradora.chat.ChatMessage;
import com.utez.integradora.chat.Status;
import com.utez.integradora.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByReceiverAndStatus(UserEntity receiver, Status status);
}