package com.example.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;

import java.util.List;

@Service
public class MessageService {

    private MessageRepository messageRepository;
    private AccountRepository accountRepository;

    /*
     * The creation of the message will be successful if and only if:
     * 1- the messageText is not blank, 
     * 2- is not over 255 characters, 
     * 3- and postedBy refers to a real, existing user. 
     * If successful, the response body should contain a JSON of the message, 
     * including its messageId. The response status should be 200, which is the default. 
     * The new message should be persisted to the database.
- If the creation of the message is not successful, the response status should be 400. (Client error)
    
     */
    //@Param messagetext
    public ResponseEntity<Message> createMessage(Message message){
        if(message.getMessageText().isBlank() || 
        message.getMessageText().length() > 255){
            return new ResponseEntity<Message>(HttpStatus.BAD_REQUEST);
        }
        if(accountRepository.findById(message.getPostedBy()).isEmpty()){
            return new ResponseEntity<Message>(HttpStatus.BAD_REQUEST);
        }
        Message newMessage = messageRepository.save(message);
        return new ResponseEntity<>(newMessage, HttpStatus.OK);
    }

    /*
     * The response body should contain a JSON representation of a list containing 
     * all messages retrieved from the database. 
     * It is expected for the list to simply be empty if there are no messages. 
     * The response status should always be 200, which is the default.
     */
    public ResponseEntity<List<Message>> getAllMessages(){
        return messageRepository.findAll();
    }

    public Message getMessageById(Integer messageId){
        return messageRepository.findById().orElsethrow(()-> new
        RuntimeException("User not found." + messageId));
    }

    public Message updatedMessage(Integer messageId, Message updatedMessage){
        Message existingMessage = messageRepository.findById(messageId)
            .orElsethrow(()-> new RuntimeJsonMappingException
            ("Message not found with id" + messageId));
    }


}
