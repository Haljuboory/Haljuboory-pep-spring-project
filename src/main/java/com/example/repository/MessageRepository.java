package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{
    Message save(Message message);
    List<Message> findAll();
    Optional<Message> findById(messageId);
    void delete(Message message);


}
