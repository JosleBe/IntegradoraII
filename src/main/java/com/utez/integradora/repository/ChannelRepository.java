package com.utez.integradora.repository;

import com.utez.integradora.entity.Channel;
import com.utez.integradora.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
    Optional<Channel> findByUser1AndUser2(UserEntity user1, UserEntity user2);
}