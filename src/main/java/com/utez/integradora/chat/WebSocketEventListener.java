package com.utez.integradora.chat;

import com.utez.integradora.entity.Message;
import com.utez.integradora.entity.UserEntity;
import com.utez.integradora.repository.UserRepository;
import com.utez.integradora.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

import java.util.List;


@Component
public class WebSocketEventListener {
/*
    @Autowired
    private ChatService chatService;

    @Autowired
    private UserRepository userRepository;

    // Este método se llama cuando un usuario se conecta
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        String username = event.getUser().getName();
        UserEntity user = userRepository.findByUsername(username);
        user.setSessionId(event.);
        userRepository.save(user);

        // Al conectarse, enviamos los mensajes pendientes
        List<ChatMessage> pendingMessages = chatService.getPendingMessages(user);
        for (ChatMessage msg : pendingMessages) {
            messagingTemplate.convertAndSendToUser(username, "/private", msg);
        }
    }

    // Este método se llama cuando un usuario se desconecta
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        String username = event.getUser().getName();
        User user = userRepository.findByUsername(username);
        user.setSessionId(null);
        userRepository.save(user);
    }

 */
}

