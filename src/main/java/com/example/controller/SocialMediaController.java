package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

import java.util.List;
import java.util.Optional;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    private  AccountService accountService;
    private  MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("/register")
    public ResponseEntity<Account> 
    registerAccount(@RequestBody Account account){
        Account accountRegistered = accountService.registerAccount(account);
            return new ResponseEntity<>(accountRegistered, HttpStatus.CREATED);
    }
    
    @GetMapping("/login")
    public ResponseEntity<Account> accountLogin(@RequestBody Account account){
        Account login = accountService.accountLogin(account);
        return new ResponseEntity<>(login, HttpStatus.ACCEPTED);
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> 
    createMessage(@RequestBody Message message){
       
        messageService.createMessage(message);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages(){
       List <Message> messages = messageService.getAllMessages();
           
           return new ResponseEntity<>(messages,HttpStatus.OK);

    } 

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Integer> 
    getMessageById(@PathVariable Integer messageId){
        messageService.getMessageById(messageId);
        return ResponseEntity.status(200).body(1);
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Message> 
    deleteMessageById(@PathVariable Integer messageId){
        messageService.deleteMessageById(messageId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Integer> 
    updateMessageById(@PathVariable Integer messageId, 
    @RequestBody Message message){
         messageService.updatedMessage(messageId, message);
        return ResponseEntity.status(200).body(1);
    }

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<Optional<Message>> 
    getUserMessages(@PathVariable Integer accountId){
          Optional<Message> messages = messageService.getUserMessages(accountId);
            return new ResponseEntity<>(messages, HttpStatus.OK);
    }


}
