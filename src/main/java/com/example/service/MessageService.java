package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private MessageRepository messageRepository;
    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }
    

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
    public Message createMessage(Message message){
        if(message.getMessageText().length() == 0 ||
            message.getMessageText().length() > 255){
            return null;
        }
        if(messageRepository.findById(message.getPostedBy()).isEmpty()){
            return null;
        }

         Message msgCreated = messageRepository.save(message);
         return msgCreated;
    }

    /*
     * The response body should contain a JSON representation of a list containing 
     * all messages retrieved from the database. 
     * It is expected for the list to simply be empty if there are no messages. 
     * The response status should always be 200, which is the default.
     */
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    //Retrieve messages by it's ID
    public Message getMessageById(Integer messageId){
        Optional<Message> existingmessage = messageRepository.findById(messageId);
        if(existingmessage.isPresent()){
            Message msg = existingmessage.get();
            //messageRepository.save(msg);
            return msg;
        }
        return null;
    }

    /*
     * The update of a message should be successful if and only if
     *  the message id already exists 
     *  and the new messageText is not blank 
     *  and is not over 255 characters. 
     */
    public Message updateMessage(Integer messageId, Message message){
        Optional<Message> existingmessage = messageRepository.findById(messageId);
        if(existingmessage.isPresent()){
            Message msg = existingmessage.get();
            msg.setMessageText(message.getMessageText());
            messageRepository.save(msg);
            return msg;
        }
        return null;

    }
    

    
    public Message deleteMessageById(Integer messageId) {
        Optional<Message> existingmessage = messageRepository.findById(messageId);
        if(existingmessage.isPresent()){
            Message msg = existingmessage.get();
            messageRepository.delete(msg);
            return msg;
        }
        return null;
       
    }


    public List<Message> getUserMessages(Integer messageId){
        return messageRepository.findByPostedBy(messageId);
        
    }

}
