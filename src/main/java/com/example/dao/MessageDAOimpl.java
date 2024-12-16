package com.example.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.example.entity.Message;

public class MessageDAOimpl implements MessageDAO {

    @Autowired
	public MessageDAOimpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int createMessage(Message message){
        String SQL_CREATE = "";
        return jdbcTemplate.update(SQL_CREATE, message.getMessageId(), 
          message.getMessageText(), message.getPostedBy(), message.getTimePostedEpoch());

    }

   @SuppressWarnings("deprecation")
public Message getMessageById(Integer messageId){
    String sql = "SELECT * FROM message WHERE message_id = ?;";
    
    return jdbcTemplate.queryForObject(sql, new Object[] { messageId }, new MessageRowMapper());
    
   }
    public boolean deleteMessageById(Integer messageId){
    String SQL_DELETE = "";
        return jdbcTemplate.update(SQL_DELETE, messageId)>0;

    }
    
    public List<Message> getAllMessages(){
String GET_SQL = "";
        return jdbcTemplate.query(GET_SQL, new MessageRowMapper());

    }

    public int updateMessageById(Message message){
        String SQL_UPDATE = "";
        return jdbcTemplate.update(SQL_UPDATE, message.getMessageId(), 
          message.getMessageText(), message.getPostedBy(), message.getTimePostedEpoch());

    }
}
