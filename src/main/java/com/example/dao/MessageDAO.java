package com.example.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.example.entity.Message;

public interface MessageDAO {

    public int createMessage(Message message);

   public Message getMessageById(Integer messageId);
    public boolean deleteMessageById(Integer messageId);
    
    public List<Message> getAllMessages();
    public int updateMessageById(Message message);
}