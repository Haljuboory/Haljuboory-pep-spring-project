package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{
    @SuppressWarnings("unchecked")
    Message save(Message message);
    List<Message> findAll();
    Optional<Message> findById(int messageId);
    static void deleteMessageById(Integer messageId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteMessageById'");
    }


}
