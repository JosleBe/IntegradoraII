package com.utez.integradora.service;

import com.utez.integradora.chat.ChatMessage;
import com.utez.integradora.chat.Status;
import com.utez.integradora.entity.Channel;
import com.utez.integradora.entity.UserEntity;
import com.utez.integradora.repository.ChannelRepository;
import com.utez.integradora.repository.MessageRepository;
import com.utez.integradora.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
/*
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private UserRepository userRepository;

    // Método para obtener o crear un canal privado
    public Channel getOrCreateChannel(UserEntity user1, UserEntity user2) {
        // Verifica si ya existe un canal entre estos dos usuarios
        return channelRepository.findByUser1AndUser2(user1, user2)
                .orElseGet(() -> createNewChannel(user1, user2));
    }

    // Crea un nuevo canal privado entre dos usuarios
    private Channel createNewChannel(UserEntity user1, UserEntity user2) {
        Channel channel = new Channel(user1, user2);
        return channelRepository.save(channel);
    }

    // Método para enviar mensajes a través de WebSocket
    public void sendMessage(String senderUsername, String receiverUsername, String message) {
        UserEntity sender = userRepository.findByUsername(senderUsername);
        UserEntity receiver = userRepository.findByUsername(receiverUsername);

        // Obtenemos o creamos el canal entre los usuarios
        Channel channel = getOrCreateChannel(sender, receiver);

        // Creamos el mensaje
        ChatMessage newMessage = new ChatMessage(sender, receiver, channel, message, Status.SENT);
        messageRepository.save(newMessage);

        // Si el receptor está conectado, enviamos el mensaje
        if (isUserConnected(receiver)) {
            messagingTemplate.convertAndSendToUser(receiver.getUsername(), "/private", newMessage);
        } else {
            // Si no está conectado, lo dejamos en "pendiente" para ser enviado cuando se conecte
            newMessage.setStatus(Status.PENDING);
            messageRepository.save(newMessage);
        }
    }

    // Verifica si el usuario está conectado
    private boolean isUserConnected(UserEntity user) {
        return user.getSessionId() != null && !user.getSessionId().isEmpty();
    }

    // Método para obtener los mensajes pendientes de un usuario cuando se conecta
    public List<ChatMessage> getPendingMessages(UserEntity user) {
        return messageRepository.findByReceiverAndStatus(user, Status.PENDING);

    
 */
}

